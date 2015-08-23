package team.afgm.rdfom.cache.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.cache.CacheManager;

public class CacheManagerTest {
	@Test
	public void testCacheManager(){
		CacheManager.setCache(CacheManager.LRU, 3, 2);
		CacheManager.put("one", "value1");
		CacheManager.put("two", "value2");
		CacheManager.put("three", "value3");
		CacheManager.get("one");
		CacheManager.put("four", "value4");
		assertNull(CacheManager.get("two"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
	
		assertNull(CacheManager.get("one"));
		assertTrue(CacheManager.getSize() == 0);
		assertNull(CacheManager.get("four"));
	}
}
