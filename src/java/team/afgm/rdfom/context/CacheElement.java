package team.afgm.rdfom.context;

public class CacheElement {
	private String type;
	private long flushInterval;
	
	public CacheElement(){
		this("FIFO", 84600);
	}
	
	public CacheElement(String type, long flushInterval){
		this.type = type;
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
}
