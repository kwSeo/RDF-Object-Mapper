package team.afgm.rdfom.cache.test;

import org.junit.Test;

import team.afgm.rdfom.cache.Cache;
import team.afgm.rdfom.cache.WeakCache;

public class WeakCacheTest {
	@Test
	public void testWeakCache(){
		Cache cache = new WeakCache("cache2", 3);
		cache.putObject("one", "value1");
		cache.putObject("two", "value2");
		cache.putObject("three", "value3");
		cache.putObject("four", "value4");
		
		for(int i=0 ; i<10000 ; i++){
			for(int j=0 ; j<10000 ; j++){
				cache.getObject("four");
				cache.getObject("three");
				cache.getObject("two");
			}
		}
		
		cache.putObject("five", "value5");
		System.out.println(cache.getObject("one"));
	}
}
