package team.afgm.rdfom.mappingsession.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

public class MappingSessionAllTest {
	private String configPath = "sample/context-config2.xml";
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build(configPath);
	
	@Test
	public void testAll(){
		MappingSession session = factory.createMappingSession("testFile2");
		
		List<Person> persons = session.selectList("afgm.seo.test2.selectOne");
		for(Person person : persons){
			System.out.println(StringUtil.toString(person));
		}
		
		System.out.println("----------------------------------------------------------");
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", "John Smith");
		Person personOne = session.selectOne("afgm.seo.test2.selectWithParam", map);
		System.out.println(StringUtil.toString(personOne));
		
		System.out.println("----------------------------------------------------------");
		
		//resultType = java.lang.Integer
		Integer age = session.selectOne("afgm.seo.test2.selectAge");
		System.out.println("Literal age: " + age);
		
		System.out.println("----------------------------------------------------------");
		
		//resultType = Integer
		int age2 = session.selectOne("afgm.seo.test2.selectAge2");
		System.out.println("Literal age2: " + age2);
		
		System.out.println("----------------------------------------------------------");
		
		boolean bool1 = session.ask("afgm.seo.testAsk.testAsk1");
		System.out.println(bool1);
	}
	
	@Before
	public void start(){
		System.out.println("MappingSession All Tset Start-----------------------------");
	}
	
	@After
	public void end(){
		System.out.println("MappingSession All Tset end-------------------------------");
	}
}
