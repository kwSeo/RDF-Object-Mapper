package team.afgm.rdfom.context;

import java.util.HashMap;
import java.util.Map;

public class Endpoints {
	private Map<String, Endpoint> endpointMap;

	public Endpoints(){
		this(new HashMap<>());
	}
	
	public Endpoints(Map<String, Endpoint> endpointMap) {
		super();
		this.endpointMap = endpointMap;
	}

	public Map<String, Endpoint> getEndpointMap() {
		return endpointMap;
	}

	public void setEndpointMap(Map<String, Endpoint> endpointMap) {
		this.endpointMap = endpointMap;
	}
	
	public Endpoint findEndpoint(String id){
		return endpointMap.get(id);
	}
	
}
