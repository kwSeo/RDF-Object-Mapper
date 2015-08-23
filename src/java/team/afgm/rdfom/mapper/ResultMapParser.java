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
			
			NodeList relationList = subXml.getNodeList("relation");
			int relationLength = relationList.getLength();
			List<ResultMap> childList = new ArrayList<>();
			
			for(int j=0 ; j<relationLength ; j++){
				XMLManager relationXml = new XMLManager(relationList.item(j));
				ResultMap relation = parseResultMap(relationXml);	//relation element랑 resultMap element랑 속성이 같음. 재귀적으로 반복
				childList.add(relation);
			}
			
			resultMap.setChildResultMap(childList);
			map.put(resultMap.getId(), resultMap);
		}
		
		return map;
	}
	
	public static ResultMap parseResultMap(XMLManager xml){
		ResultMap resultMap = new ResultMap();
		
		List<Result> results = parseResults(xml);
		String id = xml.getString("@id");
		resultMap.setId(id);
		resultMap.setResults(results);
		resultMap.setType(xml.getString("@type"));
		
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
}
