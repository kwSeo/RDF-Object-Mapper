package team.afgm.rdfom.context.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.context.CacheElement;
import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.ContextConfigFactory;

public class ContextConfigCacheTest {
	@Test
	public void testStart(){
		ContextConfig config = ContextConfigFactory.getInstance().createContextConfig("sample/context-config-cache.xml");
		CacheElement cacheElement = config.getCache();
		assertEquals("LRU", cacheElement.getType());
		assertEquals(2000, cacheElement.getFlushInterval());
		assertEquals(10, cacheElement.getCapacity());
		
		ContextConfig configWithoutCache = ContextConfigFactory.getInstance().createContextConfig("sample/context-config.xml");
		CacheElement nullCacheElement = configWithoutCache.getCache();
		assertNull(nullCacheElement);
	}
}
