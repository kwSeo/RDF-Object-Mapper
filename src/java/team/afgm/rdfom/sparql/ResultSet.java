package team.afgm.rdfom.sparql;

import java.util.List;

public interface ResultSet {
	public String getString(String name);
	public int getInt(String name);
	public double getDouble(String name);
	public String getColumn(int index);
	public List<String> getColumns();
	public boolean next();
	public void first();
	public void last();
	public void beforeFirst();
	public void numOfRows();
}
