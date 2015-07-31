package team.afgm.rdfom.context;

public class MapperResource {
	public String resource;

	public MapperResource(){
		this("");
	}
	
	public MapperResource(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
