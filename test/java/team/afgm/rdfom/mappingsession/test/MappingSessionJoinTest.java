package team.afgm.rdfom.mappingsession.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import team.afgm.bean.JoinOne;
import team.afgm.bean.Person;
import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

/**
 * 본 단위테스트는 MappingSession에서 ResultMap설정과 Join설정이 제대로 동작하는지 확인하기 위한 테스트이다. 
 * @author kwSeo
 *
 */
public class MappingSessionJoinTest {
	//별도의 테스트 샘플들을 만들어 테스트하였다. 
	private String configPath = "sample/newJoinTest/application-config.xml";
	private MappingSessionFactory factory = MappingSessionFactoryBuilder.build(configPath);
	
	/**
	 * testFile2는 'sample/joinTest/test2.rdf'파일을 의미한다.
	 * afgm.seo.test 네임스페이스는 'mapper-sample.xml'을 의미.
	 * testSelect는 resultType이 PersonMap으로 지정되어있는데 이는 ResultMap ID이다.
	 * 따로 파마미터를 요청하지 않는 쿼리이다.
	 */
	@Test
	public void testResultMap(){
		MappingSession session = factory.createMappingSession("testFile2");
		List<Person> personList = session.selectList("afgm.seo.test.testSelect");
		assertNotNull(personList);
		for(Person person : personList){
			System.out.println(StringUtil.toString(person));
		}
	}

	/**
	 * testFile2는 'sample/joinTest/test2.rdf'파일을 의미한다.
	 * afgm.seo.join.test네임스페이스는 mapper-sample.join.xml을 의미한다.
	 */
	@Test
	public void testJoin1(){
		MappingSession session = factory.createMappingSession("testFile2");
		List<JoinOne> joinOnes = session.selectList("afgm.seo.join.test.testJoin1");
		assertNotNull(joinOnes);
		assertFalse(joinOnes.isEmpty());
		for(JoinOne joinOne : joinOnes){
			System.out.println(StringUtil.toString(joinOne));
			System.out.println("\t" + StringUtil.toString(joinOne.getN()));
		}
	}
	
	@Test
	public void testJoinDetail(){
		MappingSession session = factory.createMappingSession("testFile2");
	}
	@BeforeClass
	public static void startJoinTest(){
		System.out.println("MappingSessionJoinTest start==========================================");
	}
	
	@AfterClass
	public static void endJoinTest(){
		System.out.println("MappingSessionJoinTest end============================================");
	}
}
