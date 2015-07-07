package team.afgm.rdfom.mapper;

/**
 * 
 * @author kwSeo
 *
 */
public class MapperConfigFactory {
	private static MapperConfigFactory factory;
	static{
		factory = new MapperConfigFactory();
	}
	
	private MapperConfigFactory(){}
	
	public static MapperConfigFactory getInstance(){
		return factory;
	}
	
	public MapperConfig createMapperConfig(String namespace, String mapperPath){
		return new MapperConfigImpl(
				namespace,
				SelectFactory.getInstance().createSelectsByXML(mapperPath),
				ResultMapFactory.getInstance().createResultMapsByXML(mapperPath));
	}
}
