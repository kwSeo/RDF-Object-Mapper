package team.afgm.rdfom.context;

import team.afgm.rdfom.xml.parser.XMLManager;

public class ContextConfigFactory {
	private static ContextConfigFactory factory;
	static {
		factory = new ContextConfigFactory();
	}
	
	private ContextConfigFactory() {}
	
	public static ContextConfigFactory getInstance() {
		return factory;
	}
	
	public ContextConfig createContextConfig(String contextPath) {
		XMLManager xml = new XMLManager(contextPath);
		return new ContextConfigImpl(
					EndpointsParser.parse(xml),
					NamespacesParser.parse(xml),
					MapperResourcesParser.parse(xml),
					AliasesParser.parse(xml)
				);
	}

}
