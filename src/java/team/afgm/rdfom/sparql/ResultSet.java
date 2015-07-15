package team.afgm.rdfom.sparql;

public interface ResultSet {
	public String getString(String name);
	public int getInt(String name);
	public double getDouble(String name);
	public boolean next();
	public void first();
	public void last();
	public void numOfRows();
}
