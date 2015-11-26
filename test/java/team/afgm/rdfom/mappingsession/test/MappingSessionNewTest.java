package team.afgm.rdfom.mappingsession.test;

import org.junit.Test;

import team.afgm.bean.JoinTester;
import team.afgm.rdfom.session.MappingSession;
import team.afgm.rdfom.session.MappingSessionFactory;
import team.afgm.rdfom.session.MappingSessionFactoryBuilder;
import team.afgm.rdfom.util.StringUtil;

public class MappingSessionNewTest {
	private final static String PATH = "sample/newJoinTest/application-config.xml";
	private final static MappingSessionFactory FACTORY = MappingSessionFactoryBuilder.build(PATH);
	
	@Test
	public void test(){
		MappingSession session = FACTORY.createMappingSession("testFile3");
		JoinTester tester = session.selectOne("team.afgm.test.tester.selectJoinTesters");
		System.out.println(StringUtil.toString(tester));
		System.out.println(StringUtil.toString(tester.getSubJoinTester()));
	}
}
