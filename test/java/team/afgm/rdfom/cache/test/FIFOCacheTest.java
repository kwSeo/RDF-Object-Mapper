package team.afgm.rdfom.cache.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.cache.Cache;
import team.afgm.rdfom.cache.FIFOCache;

public class FIFOCacheTest {
	@Test
	public void testCache(){
		Cache cache = new FIFOCache("cache1", 3);
		cache.putObject("one", "value1");
		cache.putObject("two", "value2");
		cache.putObject("three", "value3");
		
		assertNotNull(cache.getObject("one"));
		
		cache.putObject("four", "value4");
		
		assertNull(cache.getObject("one"));
		
		
	}
}
