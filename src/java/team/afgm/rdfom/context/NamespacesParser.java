package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

public class NamespacesParser {
	public static Namespaces parse(XMLManager xml){
		List<Namespace> namespaceList = new ArrayList<>();
		NodeList nodeList = xml.getNodeList("//namespaces/namespace");
		int length = nodeList.getLength();
		
		for(int i=0 ; i<length ; i++){
			Node namespacesNode = nodeList.item(i);
			XMLManager subXml = new XMLManager(namespacesNode);
			Namespace namespace = new Namespace(subXml.getString("@prefix"),
												subXml.getString("@url"));
			namespaceList.add(namespace);
		}
		
		return new Namespaces(namespaceList);
	}
	
}
