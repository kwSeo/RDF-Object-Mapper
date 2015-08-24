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
