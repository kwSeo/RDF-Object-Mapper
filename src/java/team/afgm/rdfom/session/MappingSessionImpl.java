package team.afgm.rdfom.session;

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

			ResultSet resultSet = endpointProcesser.executeSelect(stmt.getQuery());
			
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
			
			
			return mapper.readValue(resultSet, classType);
			
		}catch(Exception e){
			throw new RuntimeException("객체 매핑 실패." + e.getMessage());
		}
	}

	@Override
	public <T> List<T> selectList(String id) {
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

}