package team.afgm.rdfom.mapper;

import team.afgm.rdfom.xml.parser.XMLManager;

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
	
	public MapperConfig createMapperConfig(String mapperPath){
		return new MapperConfigImpl(
				new XMLManager(mapperPath).getString("//@namespace"),
				SelectFactory.getInstance().createSelectsByXML(mapperPath),
				ResultMapFactory.getInstance().createResultMapsByXML(mapperPath));
	}
}
