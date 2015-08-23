package team.afgm.rdfom.session;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.cache.CacheManager;
import team.afgm.rdfom.context.CacheElement;
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
	protected static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	
	public static MappingSessionFactory build(String contextConfigPath){
		return build(classLoader.getResourceAsStream(contextConfigPath));
	}
	
	public static MappingSessionFactory build(InputStream inputStream){
		ContextConfig contextConfig;
		try{
			contextConfig = ContextConfigFactory.getInstance().createContextConfig(inputStream);

		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new FactoryBuildException("ContextConfig Build Failed. " + e.getMessage());
		}
		
		Map<String, MapperConfig> mapperConfigMap = new HashMap<>();
		try{
			List<MapperResource> resourceList = contextConfig.getMapperResources().getMapperResourceList();
			for(MapperResource mapperResource : resourceList){
				String resourcePath = mapperResource.getResource();
				InputStream mapperInputStream = classLoader.getResourceAsStream(resourcePath);
				MapperConfig mapperConfig = MapperConfigFactory.get().createMapperConfig(mapperInputStream);
				mapperConfigMap.put(mapperConfig.getNamespace(), mapperConfig);
			}
		
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new FactoryBuildException("MapperConfig Build Failed. " + e.getMessage());
		}
		
		initCache(contextConfig.getCache());
		
		return new MappingSessionFactory(contextConfig, mapperConfigMap);
	}
	
	protected static void initCache(CacheElement cacheElement){
		if(cacheElement == null)
			return;
		
		String type = cacheElement.getType();
		long flushInterval = cacheElement.getFlushInterval();
		int capacity = cacheElement.getCapacity();
		
		CacheManager.setEnable(true);	//기본값은 false이다.
		CacheManager.setCache(type, capacity, flushInterval);
		
	}
}
