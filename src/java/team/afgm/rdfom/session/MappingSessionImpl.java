package team.afgm.rdfom.session;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.endpoint.EndpointProcessor;
import team.afgm.rdfom.mapper.Ask;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.mapper.Select;
import team.afgm.rdfom.objectmapper.ObjectMapper;
import team.afgm.rdfom.objectmapper.ResultMapHandler;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.SparqlStatement;
import team.afgm.rdfom.sparql.SparqlStatementImpl.SparqlStatementBuilder;
import team.afgm.rdfom.util.StringUtil;

/**
 * TODO 클래스가 점점 방대해져간다...
 * 아직 ContextConfig이 완성되지 않았기 때문에 완성할 수 없다.
 * MapperConfig 리스트가 아니라 맵으로 관리하자.
 * @author kwSeo
 *
 */
public class MappingSessionImpl implements MappingSession{
	private ContextConfig contextConfig;
	private Map<String, MapperConfig> mapperConfigMap;
	private EndpointProcessor endpointProcessor;
	
	public MappingSessionImpl(ContextConfig contextConfig, Map<String, MapperConfig> mapperConfigMap, EndpointProcessor endpointProcesser){
		this.contextConfig = contextConfig;
		this.mapperConfigMap = mapperConfigMap;
		this.endpointProcessor = endpointProcesser;
		
	}
	
	@Override
	public <T> T selectOne(String id) {
		return selectOne(id, null);
	}

	@Override
	public <T> T selectOne(String id, Object param) {
		List<T> list = selectList(id, toMap(param));
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	
	@Override
	public <T> T selectOne(String id, Map<String, Object> paramMap) {
		List<T> list = selectList(id, paramMap);
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	@Override
	public <T> List<T> selectList(String id, Map<String, Object> param) {
		String[] splitedId;
		String namespace;
		String realId;
		try{
			splitedId = splitNamespaceAndId(id);
			namespace = splitedId[0];
			realId = splitedId[1];
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("잘못된 네임스페이스." + e.getMessage());
		}
		

		Select select = findSelect(namespace, realId);

		SparqlStatement stmt;
		try{
			stmt = SparqlStatementBuilder.newInstance(select.getQuery(), 
													  contextConfig.getNamespaces().getNamespaces());
			if(param != null)
				stmt.setValue(param);
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error SparqlStatement setup. " + e.getMessage());
		}

		try{
			/*
			 * 지정된 ResultType이 ResultMap이냐 아니냐에 따라서 다른 ObjectMapper가 생성될 것있다. 
			 */
			ObjectMapper mapper;
			
			String resultMapId = select.getResultType();
			String resultType;
			if(isResultMap(namespace, resultMapId)){
				ResultMap resultMap = mapperConfigMap.get(namespace).findResultMap(resultMapId);
				resultType = resultMap.getType();
				mapper = createObjectMapper(resultMap);
			}else{
				resultType = resultMapId;
				mapper = createObjectMapper();
			}

			Class<T> classType;
			
			try{
				classType = (Class<T>)Class.forName(resultType);
			}catch(Exception e){
				/*
				 * ResultMap도 아니고 지정된 클래스가 있는 것도 아니다. 그렇다면 java.lang에 존재하는 클래스인지 확인한다.
				 */
				classType = (Class<T>)Class.forName("java.lang." + resultType);
			}
			
			ResultSet resultSet = endpointProcessor.executeSelect(stmt.getQuery());
			
			return mapper.readValueAsList(resultSet, classType);	//반환
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error mapping object. " + e.getMessage());
		}
	}

	@Override
	public <T> List<T> selectList(String id, Object param){
		return this.selectList(id, toMap(param));
	}
	
	@Override
	public <T> List<T> selectList(String id) {
		return this.selectList(id, null);
	}
	

	@Override
	public boolean ask(String id) {
		
		return ask(id, null);
	}

	@Override
	public boolean ask(String id, Object param) {
		return ask(id, toMap(param));
	}

	//TODO ask 메서드 만들어야함
	@Override
	public boolean ask(String id, Map<String, Object> paramMap) {
		String[] splitedId;
		String namespace;
		String realId;
		try{
			splitedId = splitNamespaceAndId(id);
			namespace = splitedId[0];
			realId = splitedId[1];
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("잘못된 네임스페이스." + e.getMessage());
		}
		
		Ask ask = findAsk(namespace, realId);
		
		SparqlStatement stmt;
		try{
			stmt = SparqlStatementBuilder.newInstance(ask.getQuery(), 
													  contextConfig.getNamespaces().getNamespaces());
			if(paramMap != null)
				stmt.setValue(paramMap);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error SparqlStatement setup. " + e.getMessage());
		}
		
		return endpointProcessor.executeAsk(stmt.getQuery());
	}

	protected String[] splitNamespaceAndId(String id){
		int i = id.lastIndexOf(".");
		String[] strs = new String[2];
		strs[0] = id.substring(0, i);
		strs[1] = id.substring(i+1, id.length());
		return strs;
	}
	
	protected boolean isResultMap(String namespace, String resultMapId){
		return (mapperConfigMap.get(namespace).findResultMap(resultMapId) != null)? true : false;
	}
	
	
	protected Select findSelect(String namespace, String queryId) {
		try{
			MapperConfig mapperConfig = mapperConfigMap.get(namespace);
			return mapperConfig.findSelect(queryId);
			
		}catch(Exception e){
			throw new RuntimeException("Error finding select with namespace and id. " + e.getMessage());
		}
	}
	
	protected Ask findAsk(String namespace, String queryId){
		try{
			return mapperConfigMap.get(namespace).findAsk(queryId);
		}catch(Exception e){
			throw new RuntimeException("Error finding ask with namespace and id. " + e.getMessage());
		}
	}

	protected <T> Map<String, Object> toMap(T obj){
		Map<String, Object> map = new HashMap<>();
		
		Class<?> classInfo = obj.getClass();
		Field[] fields = classInfo.getDeclaredFields();
		
		for(Field field : fields){
			String fieldName = field.getName();
			String methodName = "get" + StringUtil.toCamelCaseSimple(fieldName);
			
			try{
				Method method = classInfo.getMethod(methodName);
				Object returnValue = method.invoke(obj);
				map.put(fieldName, returnValue);
				
			}catch(Exception e){
				continue;
			}
		}
		
		return map;
	}
	
	protected ObjectMapper createObjectMapper(){
		return createObjectMapper(null);
	}
	
	protected ObjectMapper createObjectMapper(ResultMap resultMap){
		ObjectMapper mapper = new ObjectMapper();
		
		if(resultMap != null){
			List<Result> resultList = resultMap.getResults();
			mapper.setMappingHandler(new ResultMapHandler(resultList));
		}
		
		return mapper;
	}
	
	
}