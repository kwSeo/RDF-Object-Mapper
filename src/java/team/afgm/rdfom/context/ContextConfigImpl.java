package team.afgm.rdfom.context;


import java.util.Collections;
import java.util.List;

public class ContextConfigImpl implements ContextConfig {
	
	private List<EndPoint> endpointList;
	private List<Namespaces> namespacesList;
	private List<Mappers> mappersList;

	public ContextConfigImpl() {
		
	}

	public ContextConfigImpl(List<EndPoint> endpointList,
			List<Namespaces> namespacesList,
			List<Mappers> mappersList) {
		this.endpointList = endpointList;
		this.namespacesList = namespacesList;
		this.mappersList = mappersList;
	}

	@Override
	public EndPoint findEndPoint(String id) {
		EndPoint endpoint = EndPointFactory.getInstance().createDefaultEndPoint();
		endpoint.setId(id);
		int index = Collections.binarySearch(endpointList, endpoint);
		return endpointList.get(index);
	}

	@Override
	public List<EndPoint> getEndPointList() {
		return endpointList;
	}

	@Override
	public List<Namespaces> getNamespacesList() {
		return namespacesList;
	}

	@Override
	public List<Mappers> getMappersList() {
		return mappersList;
	}

	@Override
	public void setEndPointList(List<EndPoint> endpoint) {
		this.endpointList = endpoint;
		
	}

	@Override
	public void setNamespacesList(List<Namespaces> namespaces) {
		this.namespacesList = namespaces;
		
	}

	@Override
	public void setMappersList(List<Mappers> mappers) {
		this.mappersList = mappers;
		
	}
	
	

}
