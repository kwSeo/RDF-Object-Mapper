package team.afgm.rdfom.context;

public class ContextConfigImpl implements ContextConfig {
	private Endpoints endpoints;
	private Namespaces namespaces;
	private MapperResources mapperResources;
	

	public ContextConfigImpl(Endpoints endpoints, Namespaces namespaces, MapperResources mapperResources) {
		this.endpoints = endpoints;
		this.namespaces = namespaces;
		this.mapperResources = mapperResources;
	}

	@Override
	public Endpoints getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Endpoints endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public Namespaces getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(Namespaces namespaces) {
		this.namespaces = namespaces;
	}

	@Override
	public MapperResources getMapperResources() {
		return mapperResources;
	}

	public void setMapperResources(MapperResources mapperResources) {
		this.mapperResources = mapperResources;
	}

	@Override
	public Endpoint findEndPoint(String id) {
		return endpoints.findEndpoint(id);
	}

	@Override
	public Namespace findNamespace(int index) {
		return namespaces.getNamespace(index);
	}

	@Override
	public MapperResource findMapperResource(int index) {
		return mapperResources.getMapperResource(index);
	}
}
