package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

public class EndpointsParser {
	public static Endpoints parse(XMLManager xml){
		Map<String, Endpoint> endpointMap = new HashMap<>();
		NodeList nodeList = xml.getNodeList("//endpoints/endpoint");
		int length = nodeList.getLength();
		
		for(int i=0 ; i<length ; i++){
			Node endpointNode = nodeList.item(i);
			XMLManager subXml = new XMLManager(endpointNode);
			String id = subXml.getString("@id");
			List<Property> property = parseProperty(subXml);
			Endpoint endpoint = new Endpoint(id,
											subXml.getString("@type"),
											Boolean.valueOf(subXml.getString("@default")),
											property);
			endpointMap.put(id, endpoint);
		}
		
		return new Endpoints(endpointMap);
	}
	
	public static List<Property> parseProperty(XMLManager xml){
		List<Property> propertyList = new ArrayList<>();
		NodeList nodeList = xml.getNodeList("property");
		int length = nodeList.getLength();
		for(int i=0 ; i<length ; i++){
			XMLManager subXml = new XMLManager(nodeList.item(i));
			Property property = new Property();
			property.setName(subXml.getString("@name"));
			property.setValue(subXml.getString("."));
			propertyList.add(property);
		}
		
		return propertyList;
	}
}
