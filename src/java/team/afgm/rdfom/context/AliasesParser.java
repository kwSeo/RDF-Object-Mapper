package team.afgm.rdfom.context;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

public class AliasesParser {

	public static Aliases parse(XMLManager xml) {
		Map<String, Alias> aliasMap = new HashMap<>();
		NodeList nodeList = xml.getNodeList("//aliases/alias");
		int length = nodeList.getLength();
		
		for(int i=0; i<length; i++) {
			Node aliasNode = nodeList.item(i);
			XMLManager subXml = new XMLManager(aliasNode);
			String id = subXml.getString("@id");
			Alias alias = new Alias(id, subXml.getString("@type"));
			
			aliasMap.put(id, alias);
		}
		return new Aliases(aliasMap);
	}

}
