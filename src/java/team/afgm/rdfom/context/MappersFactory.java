package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.xml.parser.XMLManager;

public class MappersFactory {
	private static MappersFactory factory = new MappersFactory();
	
	private MappersFactory(){}
	
	public static MappersFactory getInstance() {
		return factory;
	}
	
	public Mappers createDefaultMappers() {
		return new MappersImpl();
	}
	
	public List<Mappers> createMappersByXML(String mapperPath) {
		List<Mappers> mappersList = new ArrayList<Mappers>();
		XMLManager xml = new XMLManager(mapperPath);
		int numOfMappers = xml.getInteger("count(//mappers)");
		
		for(int i=1; i<=numOfMappers; i++) {
			Mappers mappers = new MappersImpl();
			String expr = "//mappers["+i+"]";
			mappers.setMappers(getMappers(xml, i));
			mappersList.add(mappers);
		}
		
		return mappersList;
	}
	
	protected List<Mapper> getMappers(XMLManager xml, int index) {
		List<Mapper> mapperList = new ArrayList<Mapper>();
		String expr = "//mappers["+index+"]/mapper";
		int numOfMapper = xml.getInteger("count("+expr+")");

		for(int i=1; i<=numOfMapper; i++) {
			Mapper mapper = new Mapper();
			String subExpr = expr+"["+i+"]";
			mapper.setResource(xml.getString(subExpr+"/@resource"));
			mapperList.add(mapper);
		}
		
		return mapperList;
	}
}
