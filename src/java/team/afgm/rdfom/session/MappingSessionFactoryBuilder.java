package team.afgm.rdfom.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.ContextConfigFactory;
import team.afgm.rdfom.context.MapperResource;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;
import team.afgm.rdfom.session.exception.FactoryBuildException;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionFactoryBuilder {
	public static MappingSessionFactory build(String contextConfigPath){
		ContextConfig contextConfig;
		try{
			contextConfig = ContextConfigFactory.getInstance().createContextConfig(contextConfigPath);

		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new FactoryBuildException("ContextConfig Build Failed. " + e.getMessage());
		}
		
		Map<String, MapperConfig> mapperConfigMap = new HashMap<>();
		try{
			List<MapperResource> resourceList = contextConfig.getMapperResources().getMapperResourceList();
			for(MapperResource resource : resourceList){
				String resourcePath = resource.getResource();
				MapperConfig mapperConfig = MapperConfigFactory.get().createMapperConfig(resourcePath);
				mapperConfigMap.put(mapperConfig.getNamespace(), mapperConfig);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new FactoryBuildException("MapperConfig Build Failed. " + e.getMessage());
		}
		
		return new MappingSessionFactory(contextConfig, mapperConfigMap);
	}
}
