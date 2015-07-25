package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.stax.events.NamespaceImpl;

import team.afgm.rdfom.xml.parser.XMLManager;

public class NamespacesFactory {
	private static NamespacesFactory factory = new NamespacesFactory();
	
	private NamespacesFactory(){}
	
	public static NamespacesFactory getInstance() {
		return factory;
	}
	
	public Namespaces createDefaultNamespaces() {
		return new NamespacesImpl();
	}
	
	public List<Namespaces> createNamespacesByXML(String mapperPath) {
		List<Namespaces> namesapcesList = new ArrayList<Namespaces>();
		
		XMLManager xml = new XMLManager(mapperPath);
		int numOfNamespace = xml.getInteger("count(//namespaces)");
		
		for(int i=1; i<=numOfNamespace; i++) {
			Namespaces namespaces = new NamespacesImpl();
			String expr = "//namespaces["+i+"]";
			namespaces.setNamespace(getNamespaecs(xml, i));
			namesapcesList.add(namespaces);
		}
		
		return namesapcesList;
		
	}
	
	protected List<Namespace> getNamespaecs(XMLManager xml, int index) {
		List<Namespace> namespaces = new ArrayList<Namespace>();
		String expr = "//namespaces["+index+"]/namespace";
		int numOfNamespace = xml.getInteger("count("+expr+")");
		
		for(int i=1; i<=numOfNamespace; i++) {
			Namespace namespace = new Namespace();
			String subExpr = expr+"["+i+"]";
			namespace.setPrefix(xml.getString(subExpr+"/@prefix"));
			namespace.setUrl(xml.getString(subExpr+"/@url"));
			namespaces.add(namespace);
		}
		
		return namespaces;
	}
}
