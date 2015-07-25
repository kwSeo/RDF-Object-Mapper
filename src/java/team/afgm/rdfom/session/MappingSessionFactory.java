package team.afgm.rdfom.session;

import java.util.List;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.EndPoint;
import team.afgm.rdfom.endpoint.EndpointProcesser;
import team.afgm.rdfom.endpoint.FileEndpointProcesser;
import team.afgm.rdfom.mapper.MapperConfig;

public class MappingSessionFactory {
	public static final String ENDPOINT_TYPE_FILE = "file";
	public static final String ENDPOINT_TYPE_URL = "url";
	
	private ContextConfig contextConfig;
	private List<MapperConfig> mapperConfig;
	
	
	public MappingSessionFactory(ContextConfig contextConfig, List<MapperConfig> mapperConfig){
		this.contextConfig = contextConfig;
		this.mapperConfig = mapperConfig;
	}
	
	public MappingSession createMappingSession(String endpointId){
		EndpointProcesser processer;
		EndPoint endpoint = contextConfig.findEndPoint(endpointId);
		String type = endpoint.getType().trim().toLowerCase();
		switch(type){
		case ENDPOINT_TYPE_FILE:
			processer = new FileEndpointProcesser(endpoint.getProperty().get(0).getValue());
			break;
		case ENDPOINT_TYPE_URL:
			processer = new FileEndpointProcesser(endpoint.getProperty().get(0).getValue());
			break;
		default:
			throw new RuntimeException("Error Endpoint Type.");
		
		}
		MappingSessionImpl mappingSession = new MappingSessionImpl(contextConfig, mapperConfig, processer);
		return mappingSession;
	}
	
	
}
