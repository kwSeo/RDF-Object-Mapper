package team.afgm.rdfom.xml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * XPath를 사용하는 XMLManager 클래스를 테스트한다.
 * @author Administrator
 *
 */
public class XmlTest {
	private static final String MAPPER_PATH = "sample/mapper-sample.xml";
	private XMLManager manager = new XMLManager(MAPPER_PATH);
	
	@Test
	public void xmlManagerTest(){
		assertEquals("testSelect", manager.getString("//select/@id"));
		assertEquals("name", manager.getString("//resultMap/result[1]/@field"));	//XPath는 1부터 시작한다. 즉 첫번째노드는 0이 아닌 1이다.
		assertEquals(new Integer(2), manager.getInteger("count(//select)"));
	}
}
