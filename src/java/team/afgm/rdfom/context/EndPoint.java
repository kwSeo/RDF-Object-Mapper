package team.afgm.rdfom.context;

import java.util.List;

public interface EndPoint extends Comparable<EndPoint> {
	public String getId();
	public String getType();
	public List<Property> getProperty();
	public void setId(String id);
	public void setType(String type);
	public void setProperties(List<Property> properties);
}
