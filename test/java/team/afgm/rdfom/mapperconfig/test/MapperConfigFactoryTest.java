package team.afgm.rdfom.mapperconfig.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.mapper.Select;

public class MapperConfigFactoryTest {
	@Test
	public void createTest(){
		MapperConfig config = MapperConfigFactory.getInstance().createMapperConfig("sample/mapper-sample.xml");

		assertEquals("testingNamespace", config.getNamesapce());
	}
	
	@Test
	public void findTest(){
		MapperConfig config = MapperConfigFactory.getInstance().createMapperConfig("sample/mapper-sample.xml");
		long start = System.nanoTime();
		Select select = config.findSelect("testSelect");
		ResultMap resultMap = config.findResultMap("personMap");
		long end = System.nanoTime();

		assertEquals("testSelect", select.getId());
		assertEquals("personMap", resultMap.getId());

		System.out.println(end-start);
	}
}
