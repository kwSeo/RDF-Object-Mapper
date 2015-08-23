package team.afgm.rdfom.context;

public class Alias {
	private String id;
	private String type;
	
	public Alias() {
		
	}

	public Alias(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
