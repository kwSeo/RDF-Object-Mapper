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

package team.afgm.rdfom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;
/**
 * 
 * @author kwSeo
 *
 */
public class SelectParser {
	public static Map<String, Select> parse(XMLManager xml){
		Map<String, Select> map = new HashMap<>();
		
		NodeList list = xml.getNodeList("//select");
		int length = list.getLength();
		for(int i=0 ; i<length ; i++){
			Select select = new Select();
			XMLManager subXml = new XMLManager(list.item(i));
			
			String id = subXml.getString("@id");
			select.setId(id);
			select.setQuery(subXml.getString("."));
			select.setResultType(subXml.getString("@resultType"));
			
			map.put(id, select);
		}

		return map;
	}
	
}
