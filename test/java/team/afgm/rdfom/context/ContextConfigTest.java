package team.afgm.rdfom.context;

import org.junit.Test;

import team.afgm.rdfom.util.StringUtil;
import team.afgm.rdfom.xml.parser.XMLManager;

public class ContextConfigTest {
	@Test
	public void ParserTest(){
		XMLManager xml = new XMLManager("sample/context-config.xml");
		ContextConfig config = ContextConfigFactory.getInstance().createContextConfig("sample/context-config.xml");
		
		System.out.println(StringUtil.toString(config));
		System.out.println(config.findAlias("testAlias1").getType());
		
	}
}
