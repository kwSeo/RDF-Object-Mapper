/*
RDF-Object Mapping Library is a Java library 
for mapping SPARQL results to Java object(JavaBean, POJO etc.).
Copyright (C) 2015  KyeongWon Seo(kwSeo), JuHyeon Moon(jhMoon)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

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
	private static StreamMaker streamMaker = new StreamMaker(); 
	
	public static MappingSessionFactory build(String contextConfigPath){
		return build(streamMaker.getResourceAsStream(contextConfigPath));
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
				InputStream mapperInputStream = streamMaker.getResourceAsStream(resourcePath);
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
