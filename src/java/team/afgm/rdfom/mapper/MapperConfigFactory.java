package team.afgm.rdfom.mapper;

import java.io.InputStream;

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
		return createMapperConfig(xml);
	}
	
	public MapperConfig createMapperConfig(InputStream inputStream){
		XMLManager xml = new XMLManager(inputStream);
		return createMapperConfig(xml);
	}
	
	public MapperConfig createMapperConfig(XMLManager xml){
		MapperConfigImpl config = new MapperConfigImpl(
				xml.getString("//@namespace"),
				ResultMapParser.parse(xml),
				SelectParser.parse(xml),
				AskParser.parse(xml));
		
		return config;
	}
}
