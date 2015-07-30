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
			
			ResultMap resultMap = new ResultMap();
			XMLManager subXml = new XMLManager(list.item(i));
			List<Result> results = parseResults(new XMLManager(list.item(i)));
			String id = subXml.getString("@id");
			resultMap.setId(id);
			resultMap.setResults(results);
			resultMap.setType(subXml.getString("@type"));
			
			map.put(id, resultMap);
		}
		
		return map;
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
