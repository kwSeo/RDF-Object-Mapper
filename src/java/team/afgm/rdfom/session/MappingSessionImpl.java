package team.afgm.rdfom.session;

import java.util.List;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.Namespace;
import team.afgm.rdfom.endpoint.EndpointProcesser;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.Select;
import team.afgm.rdfom.objectmapper.ObjectMapper;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.ResultSetFactory;
import team.afgm.rdfom.sparql.SparqlStatement;
import team.afgm.rdfom.sparql.SparqlStatementImpl.SparqlStatementBuilder;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionImpl implements MappingSession{
	private ContextConfig contextConfig;
	private List<MapperConfig> mapperConfigs;
	private EndpointProcesser endpointProcesser;
	
	public MappingSessionImpl(){
		
	}
	
	
	
	public MappingSessionImpl(ContextConfig contextConfig, List<MapperConfig> mapperConfig, EndpointProcesser endpointProcesser) {
		this.contextConfig = contextConfig;
		this.mapperConfigs = mapperConfig;
		this.endpointProcesser = endpointProcesser;
	}


	/**
	 * TODO mapperConfig은 여러개가 존재할 수 있는데 프로토타입에서는 하나라고 가정한다.
	 */
	@Override
	public <T> T selectOne(String id, Class<T> classType) {
		Select select = mapperConfigs.get(0).findSelect(id);
		SparqlStatement stmt = SparqlStatementBuilder.newInstance(select.getQuery());
		List<Namespace> namespaces = contextConfig.getNamespacesList().get(0).getNamespace();
		for(Namespace namespace : namespaces){
			stmt.addNamespace(namespace);
		}
		
		ResultSet resultSet = ResultSetFactory.getInstance().
				createJAXBResultSet(endpointProcesser.executeSelectAsString(stmt.getQuery()));
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(resultSet, classType);
	}

	@Override
	public <T> List<T> selectList(String id, Class<T> classType) {
		// TODO Auto-generated method stub
		return null;
	}

}
