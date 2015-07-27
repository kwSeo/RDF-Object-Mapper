package team.afgm.rdfom.session;

import java.util.List;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.endpoint.EndpointProcesser;
import team.afgm.rdfom.mapper.MapperConfig;

/**
 * TODO
 * 아직 ContextConfig이 완성되지 않았기 때문에 완성할 수 없다.
 * MapperConfig 리스트가 아니라 맵으로 관리하자.
 * @author kwSeo
 *
 */
public class MappingSessionImpl implements MappingSession{

	public MappingSessionImpl(ContextConfig contextConfig, List<MapperConfig> mappingConfig, EndpointProcesser processer){
		
	}
	
	@Override
	public <T> T selectOne(String id, Class<T> classType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectList(String id, Class<T> classType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}