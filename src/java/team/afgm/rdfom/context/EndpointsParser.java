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
