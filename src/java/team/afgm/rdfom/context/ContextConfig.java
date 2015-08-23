package team.afgm.rdfom.context;

public interface ContextConfig {
	
	public Endpoint findEndPoint(String id);
	
	public Namespace findNamespace(int index);
	
	public MapperResource findMapperResource(int index);

	public Endpoints getEndpoints();
	
	public Namespaces getNamespaces();
	
	public MapperResources getMapperResources();
	
	public Aliases getAliases();
	
	public Alias findAlias(String id);
	
	public CacheElement getCache();
}