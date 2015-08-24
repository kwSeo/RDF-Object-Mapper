package team.afgm.rdfom.mappingsession.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import team.afgm.bean.JoinTester;
import team.afgm.bean.Tester;
import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

public class MappingSession1BetaTest {
	private final String CONFIG_PATH = "sample/beta/context-config.xml"; 
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build(CONFIG_PATH);
	
	/**
	 * selectOne을 통한 원시타입 결과 얻기.
	 * Map과 클래스 파라미터 테스트.
	 * join & alias 테스트.
	 * 
	 * 개선할점
	 * 		parameter는 현재 int, double, String 밖에 안됨...
	 * 		join을 할때 서브클래스는 alias 적용 안됨...
	 * 
	 */
	@Test
	public void testPrimitive(){
		MappingSession session = factory.createMappingSession("testFile");
		int intValue = session.selectOne("team.afgm.beta.test.selectInt");
		System.out.println(intValue);
		
		Map<String, Object> param1 = new HashMap<>();
		param1.put("stringData", "Hello World");
		double doubleValue = session.selectOne("team.afgm.beta.test.selectDoubleWithParam", param1);
		System.out.println(doubleValue);
		
		Testparam param2 = new Testparam();
		param2.setIntData(intValue);
		byte byteValue = session.selectOne("team.afgm.beta.test.selectByteWithParam", param2);
		System.out.println(byteValue);
		
		short shortValue = session.selectOne("team.afgm.beta.test.selectShort");
		System.out.println(shortValue);
		
		long longValue = session.selectOne("team.afgm.beta.test.selectLong");
		System.out.println(longValue);
		
		boolean booleanValue = session.selectOne("team.afgm.beta.test.selectBoolean");
		System.out.println(booleanValue);
		
		String stringValue = session.selectOne("team.afgm.beta.test.selectString");
		System.out.println(stringValue);
		
		float floatValue = session.selectOne("team.afgm.beta.test.selectFloat");
		System.out.println(floatValue);
		
		Tester tester = session.selectOne("team.afgm.beta.test.selectTester");
		System.out.println(StringUtil.toString(tester));
		
		JoinTester joinTester = session.selectOne("team.afgm.beta.test.selectJoinTesters");
		System.out.println(StringUtil.toString(joinTester));
		System.out.println(StringUtil.toString(joinTester.getSubJoinTester()));
		
		
	}
	
	@Test
	public void testCache(){
		long start, end;
		MappingSession session = factory.createMappingSession("dbpedia");
		
		Tester tester = session.selectOne("team.afgm.beta.test.selectTester");
		
		start = System.nanoTime();
		List<String> strList1 = session.selectList("team.afgm.beta.dbpedia.test.selectConcept");
		end = System.nanoTime();
		System.out.println("No cache : " + (end-start));
		
		start = System.nanoTime();
		List<String> strList2 = session.selectList("team.afgm.beta.dbpedia.test.selectConcept");
		end = System.nanoTime();
		System.out.println("Cach Hit : " + (end-start));
		
		start = System.nanoTime();
		List<String> strList3 = session.selectList("team.afgm.beta.dbpedia.test.selectConcept");
		end = System.nanoTime();
		System.out.println("Cach Hit : " + (end-start));
		
		start = System.nanoTime();
		List<String> strList4 = session.selectList("team.afgm.beta.dbpedia.test.selectConcept");
		end = System.nanoTime();
		System.out.println("Cach Hit : " + (end-start));
		
		start = System.nanoTime();
		List<String> strList5 = session.selectList("team.afgm.beta.dbpedia.test.selectConcept");
		end = System.nanoTime();
		System.out.println("Cach Hit : " + (end-start));
	}
}
