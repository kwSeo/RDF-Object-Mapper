package team.afgm.rdfom.context;

import java.util.List;

public interface ContextConfig {
	
	public EndPoint findEndPoint(String id);
	public List<EndPoint> getEndPointList();
	
	public List<Namespaces> getNamespacesList();
	
	public List<Mappers> getMappersList();
	
	public void setEndPointList(List<EndPoint> endpoint);
	public void setNamespacesList(List<Namespaces> namespaces);
	public void setMappersList(List<Mappers> mappers);

}