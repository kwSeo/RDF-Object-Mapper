package team.afgm.rdfom.context;

import org.w3c.dom.Node;

import team.afgm.rdfom.xml.parser.XMLManager;

public class CacheElementParser {
	public static CacheElement parse(XMLManager xml){
		Node cacheNode = xml.getNode("/configuration/cache");
		if(cacheNode == null)
			return null;
		
		XMLManager cacheXml = new XMLManager(cacheNode);
		CacheElement cacheElement = new CacheElement(
				cacheXml.getString("@type"),
				cacheXml.getInteger("@capacity"),
				cacheXml.getInteger("@flushInterval"));
		
		return cacheElement;
		
	}
}
