package team.afgm.rdfom.mapper.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import team.afgm.rdfom.mapper.Ask;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;
import team.afgm.rdfom.util.StringUtil;

public class AskTest {
	private MapperConfig config = null;
	@Before
	public void initMapperConfig(){
		MapperConfigFactory factory = MapperConfigFactory.get();
		config = factory.createMapperConfig("sample/mapper-sample-ask.xml");
	}
	
	@Test
	public void testAskParser(){
		Ask ask = config.findAsk("testAsk1");
		assertNotNull(ask);
		System.out.println(StringUtil.toString(ask));
	}
}
