package team.afgm.rdfom.cache.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.cache.Cache;
import team.afgm.rdfom.cache.LRUCache;

public class LRUCacheTest {
	@Test
	public void testLRUCache(){
		Cache cache = new LRUCache("testCache3", 3);
		cache.putObject("one", "value1");
		cache.putObject("two", "value2");
		cache.putObject("three", "value3");
		
		System.out.println(cache.getObject("one"));
		System.out.println(cache.getObject("two"));
		System.out.println(cache.getObject("three"));
		
		cache.getObject("one");
		cache.getObject("three");
		cache.getObject("one");
		
		cache.putObject("four", "value4");
		assertNull(cache.getObject("two"));
	}
}
