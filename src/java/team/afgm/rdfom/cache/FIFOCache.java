package team.afgm.rdfom.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIFOCache implements Cache{
	private String id;
	private int capacity;
	private Map<Object, Object> cacheMap;
	
	public FIFOCache(String id){
		this(id, DEFAULT_SIZE);
	}
	
	public FIFOCache(String id, int capacity){
		this.id = id;
		this.capacity = capacity;
		this.cacheMap = new LinkedHashMap<Object, Object>(capacity){
			@Override
			protected boolean removeEldestEntry(Map.Entry<Object,Object> eldest) {
		        return (this.size() > capacity);
		    }
		};
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		cacheMap.put(key, value);
	}

	@Override
	public Object getObject(Object key) {
		return cacheMap.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cacheMap.remove(key);
	}

	@Override
	public void clear() {
		cacheMap.clear();
	}

	@Override
	public int getSize() {
		return cacheMap.size();
	}

}
