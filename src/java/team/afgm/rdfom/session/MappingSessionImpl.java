package team.afgm.rdfom.session;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.endpoint.EndpointProcesser;
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
 * TODO 메소드간에 코드가 많이 중복된다. 해결해야한다!
 * 아직 ContextConfig이 완성되지 않았기 때문에 완성할 수 없다.
 * MapperConfig 리스트가 아니라 맵으로 관리하자.
 * @author kwSeo
 *
 */
public class MappingSessionImpl implements MappingSession{
	private ContextConfig contextConfig;
	private Map<String, MapperConfig> mapperConfigMap;
	private EndpointProcesser endpointProcesser;
	
	public MappingSessionImpl(ContextConfig contextConfig, Map<String, MapperConfig> mapperConfigMap, EndpointProcesser endpointProcesser){
		this.contextConfig = contextConfig;
		this.mapperConfigMap = mapperConfigMap;
		this.endpointProcesser = endpointProcesser;
		
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
			throw new RuntimeException("잘못된 네임스페이스." + e.getMessage());
		}
		
		MapperConfig mapperConfig;
		Select select;
		try{
			//해당하는 Mapper 설정을 찾음
			mapperConfig = mapperConfigMap.get(namespace);
			//해당하는 Mapper 설정 내의 선택한 Select를 찾음.
			select = mapperConfig.findSelect(realId);
		}catch(Exception e){
			throw new RuntimeException("네임스페이스 또는 아이디에 해당하는 개체가 없음." + e.getMessage());
		}

		SparqlStatement stmt;
		ObjectMapper mapper;
		try{
			stmt = SparqlStatementBuilder.newInstance(select.getQuery(), 
													  contextConfig.getNamespaces().getNamespaces());
			if(param != null)
				stmt.setValue(param);
			
			
			String resultTypeId = select.getResultType();
			String resultType;
			mapper = new ObjectMapper();
			if(isResultMap(namespace, resultTypeId)){
				ResultMap resultMap = mapperConfigMap.get(namespace).findResultMap(resultTypeId);
				resultType = resultMap.getType();
				List<Result> resultList = resultMap.getResults();
				mapper.setMappingHandler(new ResultMapHandler(resultList));
				
			}else{
				resultType = select.getResultType();
			}
			Class<T> classType = (Class<T>)Class.forName(resultType);
			
			ResultSet resultSet = endpointProcesser.executeSelect(stmt.getQuery());
			
			return mapper.readValueAsList(resultSet, classType);
			
		}catch(Exception e){
			throw new RuntimeException("객체 매핑 실패." + e.getMessage());
		}
	}


	public <T> List<T> selectList(String id, Object param){
		return this.selectList(id, toMap(param));
	}
	
	@Override
	public <T> List<T> selectList(String id) {
		return this.selectList(id, null);
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
			throw new RuntimeException("네임스페이스 또는 아이디에 해당하는 개체가 없음." + e.getMessage());
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
}