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
