package team.afgm.rdfom.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;

public class MapperConfigFactoryTest {
	@Test
	public void test(){
		MapperConfig config = MapperConfigFactory.getInstance().createMapperConfig("testingNamespace", "sample/mapper-sample.xml");
		
		assertEquals("testingNamespace", config.getNamesapce());
		
	}
}
