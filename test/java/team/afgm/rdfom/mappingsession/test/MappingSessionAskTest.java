package team.afgm.rdfom.mappingsession.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;

public class MappingSessionAskTest {
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build("sample/context-config2.xml");;
	
	@Before
	public void beforeAsk(){
		System.out.println("setup-----------------------------------------------");
	}
	
	@After
	public void afterAsk(){
		System.out.println("end-------------------------------------------------");
	}
	
	@Test
	public void testAsk(){
		MappingSession session = factory.createMappingSession("testFile2");
		boolean result1 = session.ask("afgm.seo.testAsk.testAsk1");
		assertTrue(result1);
		System.out.println(result1);
		
		Map<String, Integer> param = new HashMap<>();
		param.put("age", 25);
		boolean result2 = session.ask("afgm.seo.testAsk.testAsk2", param);
		assertTrue(result2);
		System.out.println(result2);
		
		Person paramClass = new Person();
		paramClass.setAge(25);
		boolean result3 = session.ask("afgm.seo.testAsk.testAsk2", paramClass);
		assertTrue(result3);
		System.out.println(result3);
		
		Person paramClass2 = new Person();
		paramClass2.setAge(10);
		boolean result4 = session.ask("afgm.seo.testAsk.testAsk2", paramClass2);
		assertFalse(result4);
		System.out.println(result4);
	}
}



