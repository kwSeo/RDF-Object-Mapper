package team.afgm.rdfom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * 
 * @author kwSeo
 *
 */
public class AskParser {
	public static Map<String, Ask> parse(XMLManager xml){
		Map<String, Ask> map = new HashMap<>();
		NodeList nodeList = xml.getNodeList("//ask");
		int length = nodeList.getLength();
		
		for(int i=0 ; i<length ; i++){
			Node node = nodeList.item(i);
			XMLManager askXml = new XMLManager(node);
			String id = askXml.getString("@id");
			String query = askXml.getString(".");
			
			Ask ask = new Ask(id, query);
			map.put(id, ask);
		}
		
		return map;
	}
}
