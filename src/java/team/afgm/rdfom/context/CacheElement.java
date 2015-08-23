package team.afgm.rdfom.context;

public class CacheElement {
	private String type;
	private long flushInterval;
	private int capacity;
	
	public CacheElement(){
		this("FIFO", 84600, 1024);
	}
	
	public CacheElement(String type, int capacity, long flushInterval){
		this.type = type;
		this.capacity = capacity;
		this.flushInterval = flushInterval;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getFlushInterval() {
		return flushInterval;
	}

	public void setFlushInterval(long flushInterval) {
		this.flushInterval = flushInterval;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
