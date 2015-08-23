package team.afgm.rdfom.mappingsession.test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import team.afgm.bean.JoinTester;
import team.afgm.bean.PrimitiveTester;
import team.afgm.bean.Tester;
import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

public class MappingSessionTestFile3Test {
	private static final String CONFIG_PATH = "sample/joinTest/application-config.xml";
	private static MappingSessionFactory factory;
	
	/**
	 * 아래의 테스트는 Tester 객체에 제대로 매핑이 되는지 확인하기 위한 것이다. Tester 클래스는 모든 멤버필드들이 Wapper클래스로 선언되어있다.
	 */
	@Test
	public void test1(){
		MappingSession session = factory.createMappingSession("testFile3");
		List<Tester> testerList = session.selectList("team.afgm.test.tester.selectTesters");
		for(Tester tester : testerList){
			System.out.println(StringUtil.toString(tester));
		}
	}
	
	/**
	 * 아래의 테스트는 PrimitiveTester 객체에 제대로 매핑이 되는지 확인하기 위한 것이다. 
	 * PrimitiveTester 클래스는 모든 멤버필드들이 원시타입으로 선언되었으며 setter는 없고  getter만 정의되었다.
	 */
	@Test
	public void test2(){
		MappingSession session = factory.createMappingSession("testFile3");
		List<PrimitiveTester> testerList = session.selectList("team.afgm.test.tester.selectPrimitiveTesters");
		for(PrimitiveTester tester : testerList){
			System.out.println(StringUtil.toString(tester));
		}
	}
	
	/**
	 * 아래의 테스트는 JoinTester 객체에 제대로 매핑이 되는지 확인하기 위한 것이다. 
	 * JoinTester 클래스는 모든 멤버필드들이 원시타입으로 선언되었으며 setter는 없고  getter만 정의되었다.
	 * 또한 조인기능이 제대로 동작하는 확인하기 위해서 ResultMap을 통해 매핑설정이 되어있으며, 
	 * JoinTester클래스는 내부적으로 SubJoinTester 클래스를 가지고 있다.
	 */
	@Test
	public void test3Join(){
		MappingSession session = factory.createMappingSession("testFile3");
		List<JoinTester> testerList = session.selectList("team.afgm.test.tester.selectJoinTesters");
		for(JoinTester tester : testerList){
			System.out.println(StringUtil.toString(tester));
			System.out.println("\t" + StringUtil.toString(tester.getSubJoinTester()));
		}
	}
	
	@BeforeClass
	public static void beforeClass(){
		factory = MappingSessionFactoryBuilder.build(CONFIG_PATH);
		System.out.println("start MappingSessionTestFile3Test===========================================================");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("end MappingSessionTestFile3Test=============================================================\n");
	}
	
	@Before
	public void before(){
		System.out.println("start test ---------------------------------------------------------------------------------");
	}
	
	@After
	public void after(){
		System.out.println("end test -----------------------------------------------------------------------------------\n");
	}

}
