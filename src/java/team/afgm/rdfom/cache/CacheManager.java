/*
RDF-Object Mapping Library is a Java library 
for mapping SPARQL results to Java object(JavaBean, POJO etc.).
Copyright (C) 2015  KyeongWon Seo(kwSeo), JuHyeon Moon(jhMoon)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package team.afgm.rdfom.cache;

/**
 * 
 * @author kwSeo
 *
 */
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
