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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * 
 * @author kwSeo
 *
 */
public class ResultMapParser {
	public static Map<String, ResultMap> parse(XMLManager xml){
		Map<String, ResultMap> map = new HashMap<>();
		
		NodeList list = xml.getNodeList("//resultMap");
		int length = list.getLength();
		for(int i=0 ; i<length ; i++){
			XMLManager subXml = new XMLManager(list.item(i));
			ResultMap resultMap = parseResultMap(subXml);
			map.put(resultMap.getId(), resultMap);
		}
		
		return map;
	}
	
	public static ResultMap parseResultMap(XMLManager xml){
		ResultMap resultMap = new ResultMap();
		
		resultMap.setId(xml.getString("@id"));
		resultMap.setType(xml.getString("@type"));
		resultMap.setResults(parseResults(xml));
		//join 파싱, relation말고 이걸 사용하자.
		resultMap.setJoinList(parseJoins(xml));
		
		return resultMap;
	}
	
	public static List<Result> parseResults(XMLManager xml){
		List<Result> results = new ArrayList<>();
		NodeList list = xml.getNodeList("result");
		int length = list.getLength();
		for(int i=0 ; i<length ; i++){
			XMLManager subXml = new XMLManager(list.item(i));
			
			Result result = new Result();
			result.setColumn(subXml.getString("@column"));
			result.setField(subXml.getString("@field"));
			
			results.add(result);
		}
		
		return results;
	}
	
	public static List<Join> parseJoins(XMLManager xml){
		List<Join> joins = new ArrayList<>();
		NodeList list = xml.getNodeList("join");
		int length = list.getLength();
		
		for(int i=0 ; i<length ; i++){
			XMLManager subXml = new XMLManager(list.item(i));
			
			Join join = new Join(
					subXml.getString("@resultMapId"),
					subXml.getString("@field"));
			joins.add(join);
		}
		
		return joins;
	}
}
