package team.afgm.rdfom.context;

public class ContextConfigFactory {
	private static ContextConfigFactory factory;
	static {
		factory = new ContextConfigFactory();
	}
	
	private ContextConfigFactory() {}
	
	public static ContextConfigFactory getInstance() {
		return factory;
	}
	
	public ContextConfig createContextConfig(String mapperPath) {
		return new ContextConfigImpl(
				EndPointFactory.getInstance().createEndPointByXML(mapperPath),
				NamespacesFactory.getInstance().createNamespacesByXML(mapperPath),
				MappersFactory.getInstance().createMappersByXML(mapperPath)
				);
	}

}
