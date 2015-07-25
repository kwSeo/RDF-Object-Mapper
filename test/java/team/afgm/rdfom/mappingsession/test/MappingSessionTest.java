package team.afgm.rdfom.mappingsession.test;

import org.junit.Test;

import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

public class MappingSessionTest {
	@Test
	public void mappingSessionShowingTest(){
		MappingSession session = MappingSessionFactoryBuilder.build("sample/context-config2.xml").createMappingSession("testFile");
		Person person = session.selectOne("testSelect", Person.class);
		System.out.println(StringUtil.toString(person));
	}
}
