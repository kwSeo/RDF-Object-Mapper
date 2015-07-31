package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {
	public static final String TEXT_EMPTY = "";
	private String id;
	private String type;
	private boolean isDefault;
	private List<Property> properties;
	
	public Endpoint() {
		this(TEXT_EMPTY, TEXT_EMPTY, false, new ArrayList<>());
	}

	public Endpoint(String id, String type, boolean isDefault, List<Property> properties) {
		this.id = id;
		this.type = type;
		this.isDefault = isDefault;
		this.properties = properties;
	}

	public boolean isDefault(){
		return this.isDefault;
	}
	
	public void setDefault(boolean isDefault){
		this.isDefault = isDefault;
	}
	
	public Endpoint(boolean isDefault) {
		super();
		this.isDefault = isDefault;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;

	}

	public void setType(String type) {
		this.type = type;

	}

	public List<Property> getProperty() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
		
	}

}
