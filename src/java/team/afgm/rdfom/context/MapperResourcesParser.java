package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.parser.XMLManager;

public class MapperResourcesParser {
	public static MapperResources parse(XMLManager xml){
		List<MapperResource> mapperResourceList = new ArrayList<>();
		NodeList nodeList = xml.getNodeList("//mappers/mapper");
		int length = nodeList.getLength();
		
		for(int i=0 ; i<length ; i++){
			Node mapperResourceNode = nodeList.item(i);
			XMLManager subXml = new XMLManager(mapperResourceNode);
			MapperResource mapperResource = new MapperResource(subXml.getString("@resource"));
			mapperResourceList.add(mapperResource);
		}
		
		return new MapperResources(mapperResourceList);
	}
}
