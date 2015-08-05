package team.afgm.rdfom.mappingsession.test;

import java.util.List;

import org.junit.Test;

import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionTest {
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build("sample/context-config.xml");;

	@Test
	public void testMappingSessionResultMap(){
		System.out.println("-------------------------------------------");
		MappingSession session = factory.createMappingSession("testFile");
		
		Person personOne = session.selectOne("afgm.seo.test2.selectOne");
		
		System.out.println(StringUtil.toString(personOne));
		
		List<Person> persons = session.selectList("afgm.seo.test2.selectOne");
		
		for(Person person : persons){
			System.out.println(StringUtil.toString(person));
		}
		
		List<Person> persons2 = session.selectList("afgm.seo.test2.selectTwo");
		
		for(Person person : persons2){
			System.out.println(StringUtil.toString(person));
		}
		System.out.println("-------------------------------------------");
	}
}
