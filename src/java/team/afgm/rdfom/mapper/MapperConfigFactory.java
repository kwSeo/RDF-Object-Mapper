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
	
	public static MapperConfigFactory get(){
		return factory;
	}
	
	public MapperConfig createMapperConfig(String mapperPath){
		XMLManager xml = new XMLManager(mapperPath);
		
		MapperConfigImpl config = new MapperConfigImpl(
				xml.getString("//@namespace"),
				ResultMapParser.parse(xml),
				SelectParser.parse(xml),
				AskParser.parse(xml));
		
		return config;
	}
}
