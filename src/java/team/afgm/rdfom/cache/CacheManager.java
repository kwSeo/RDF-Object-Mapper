package team.afgm.rdfom.cache;

public class CacheManager {
	public static final String FIFO = "FIFO";
	public static final String LRU = "LRU";
	public static final String WEAK = "Weak";
	
	private static Cache cache = new FIFOCache("FIFOCache", 86400);
	
	private static long flushInterval = 86400;
	private static long settedTime = System.currentTimeMillis() / 1000;
	private static boolean enable = false;
	
	public static void setEnable(boolean isEnable){
		enable = isEnable;
		if(enable == false){
			cache = null;
		}else{
			cache = new FIFOCache("FIFOCache", 86400);
		}
	}
	
	public static void setCache(String cacheName){
		setCache(cacheName, Cache.DEFAULT_SIZE);
	}
	
	
	public static void setCache(String cacheName, int capacity){
		setCache(cacheName, capacity, 86400);
	}
	
	public static void setCache(String cacheName, int capacity, long flushInterval){
		switch(cacheName){
		case FIFO:
			cache = new FIFOCache("FIFOCache", capacity);
			break;
		case LRU:
			cache = new LRUCache("LRUCache", capacity);
			break;
		case WEAK:
			cache = new WeakCache("WeakCache", capacity);
			break;
		}
		CacheManager.flushInterval = flushInterval;
		
	}
	
	public static Object get(Object key){
		if(enable == false)
			return null;
		
		checkFlushInterval();
		return cache.getObject(key);
	}
	
	public static void put(Object key, Object value){
		if(enable == false)
			return;
		
		checkFlushInterval();
		cache.putObject(key, value);
	}
	
	public static void clear(){
		if(enable == false)
			return;
		
		cache.clear();
	}
	
	public static Object remove(Object key){
		if(enable == false)
			return null;
		
		return cache.removeObject(key);
	}
	
	public static boolean checkFlushInterval(){
		if(enable == false)
			return false;
		
		long currentSec = System.currentTimeMillis() / 1000;
		long interval = currentSec - settedTime;
		if(interval >= flushInterval){
			clear();
			settedTime = System.currentTimeMillis() / 1000;
			return true;
		}else
			return false;
	}
	
	public static int getSize(){
		if(enable == false)
			return 0;
		
		return cache.getSize();
	}
}
