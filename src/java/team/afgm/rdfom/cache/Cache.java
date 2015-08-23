package team.afgm.rdfom.cache;

public interface Cache {
	public static final int DEFAULT_SIZE = 1024;
	
	public String getId();
	public void putObject(Object key, Object value);
	public Object getObject(Object key);
	public Object removeObject(Object key);
	public void clear();
	public int getSize();
}
