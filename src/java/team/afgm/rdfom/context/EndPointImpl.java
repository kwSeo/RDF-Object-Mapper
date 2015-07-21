package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

public class EndPointImpl implements EndPoint {
	public static final String TEXT_EMPTY = "";
	private String id;
	private String type;
	private List<Property> properties;
	
	public EndPointImpl() {
		this.id = this.type = TEXT_EMPTY;
		this.properties = new ArrayList<Property>();
	}

	public EndPointImpl(String id, String type, List<Property> properties) {
		super();
		this.id = id;
		this.type = type;
		this.properties = properties;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	@Override
	public void setType(String type) {
		this.type = type;

	}

	@Override
	public int compareTo(EndPoint o) {
		return this.id.compareTo(o.getId());
	}

	@Override
	public List<Property> getProperty() {
		return properties;
	}

	@Override
	public void setProperties(List<Property> properties) {
		this.properties = properties;
		
	}

}
