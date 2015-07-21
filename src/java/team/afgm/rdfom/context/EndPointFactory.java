package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.xml.parser.XMLManager;

public class EndPointFactory {
	private static EndPointFactory factory = new EndPointFactory();
	
	public static EndPointFactory getInstance() {
		return factory;
	}
	
	public EndPoint createDefaultEndPoint() {
		return new EndPointImpl();
	}
	
	public List<EndPoint> createEndPointByXML(String configPath) {
		List<EndPoint> endPointList = new ArrayList<EndPoint>();
		
		XMLManager xml = new XMLManager(configPath);
		int numOfEndPoint = xml.getInteger("count(//endpoints/endpoint)");
		
		for(int i=1; i<numOfEndPoint; i++) {
			String expr = "//endpoints/endpoint["+i+"]";
			String id = xml.getString(expr+"/@id");
			String type = xml.getString(expr + "/@type");
			List<Property> properties = getProperties(xml, i);
			endPointList.add(new EndPointImpl(id, type, properties));
		}
		
		return endPointList;
	}
	
	protected List<Property> getProperties(XMLManager xml, int index) {
		List<Property> properties = new ArrayList<Property>();
		String expr = "//endpoints/endpoint["+index+"]/property";
		int numOfResult = xml.getInteger("count("+expr+")");
		
		for(int i=1; i<numOfResult; i++) {
			Property property = new Property();
			String subExpr = expr+"["+i+"]";
			property.setName(xml.getString(subExpr+"/@name"));
			property.setValue(xml.getString(subExpr));
			properties.add(property);
		}
		
		return properties;
	}
}
