package team.afgm.rdfom.cache;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakCache implements Cache{
	private String id;
	private int capacity;
	private Map<Object, Object> cacheMap;
	
	public WeakCache(String id, int capacity){
		this.id = id;
		this.capacity = capacity;
		this.cacheMap = new WeakHashMap<>(capacity);
	}
	
	public WeakCache(String id){
		this(id, DEFAULT_SIZE);
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
