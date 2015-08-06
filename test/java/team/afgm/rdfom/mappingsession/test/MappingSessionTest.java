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
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build("sample/context-config2.xml");;

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
		
		//TODO 결과값이 하나도 없으면 에러
		List<Person> persons2 = session.selectList("afgm.seo.test2.selectTwo");
		
		for(Person person : persons2){
			System.out.println(StringUtil.toString(person));
		}
		System.out.println("-------------------------------------------");
	}
	
	@Test
	public void testMappingSessionIntegerType(){
		MappingSession session = factory.createMappingSession("testFile2");
		Person person = session.selectOne("afgm.seo.test2.selectWithInt");
		System.out.println(StringUtil.toString(person));
	}
}
