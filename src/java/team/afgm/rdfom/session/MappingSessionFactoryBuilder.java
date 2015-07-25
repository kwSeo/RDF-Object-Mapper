package team.afgm.rdfom.session;

import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.ContextConfigFactory;
import team.afgm.rdfom.context.Mapper;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;

public class MappingSessionFactoryBuilder {
	public static MappingSessionFactory build(String contextConfigPath){
		ContextConfig contextConfig = ContextConfigFactory.getInstance().createContextConfig(contextConfigPath);
		List<Mapper> mappers = contextConfig.getMappersList().get(0).getMappers();
		List<MapperConfig> mapperConfigs = new ArrayList<>();
		for(Mapper mapper : mappers){
			MapperConfig mapperConfig = MapperConfigFactory.getInstance().createMapperConfig(mapper.getResource());
			mapperConfigs.add(mapperConfig);
		}
		
		return new MappingSessionFactory(contextConfig, mapperConfigs);
	}
}
