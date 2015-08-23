package team.afgm.rdfom.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache implements Cache{
	private String id;
	private int capacity;
	private Map<Object, Object> cacheMap = new HashMap<>();
	private LinkedList<Object> keyList = new LinkedList<>();
	
	public LRUCache(String id){
		this(id, DEFAULT_SIZE);
	}
	
	public LRUCache(String id, int capacity){
		this.id = id;
		this.capacity = capacity;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if(capacity <= cacheMap.size()){
			Object removingKey = keyList.removeLast();
			cacheMap.remove(removingKey);
		}
		keyList.addFirst(key);
		cacheMap.put(key, value);
	}

	@Override
	public Object getObject(Object key) {
		keyList.remove(key);
		keyList.addFirst(key);
		return cacheMap.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		keyList.remove(key);
		return cacheMap.remove(key);
	}

	@Override
	public void clear() {
		keyList.clear();
		cacheMap.clear();
	}

	@Override
	public int getSize() {
		return cacheMap.size();
	}
	
}
