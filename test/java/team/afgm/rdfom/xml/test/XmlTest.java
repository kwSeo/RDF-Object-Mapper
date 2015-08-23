package team.afgm.rdfom.xml.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Node;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * XPath를 사용하는 XMLManager 클래스를 테스트한다.
 * @author kwSeo
 *
 */
public class XmlTest {
	private static final String MAPPER_PATH = "sample/mapper-sample.xml";
	private XMLManager manager = new XMLManager(MAPPER_PATH);
	
	@Test
	public void xmlManagerTest(){
		assertEquals("testSelect", manager.getString("//select/@id"));
		assertEquals("name", manager.getString("//resultMap/result[1]/@field"));	//XPath는 1부터 시작한다. 즉 첫번째노드는 0이 아닌 1이다.
		assertEquals(new Integer(3), manager.getInteger("count(//select)"));
		
		Node node = manager.getNode("//select");
		//System.out.println(node.getTextContent());
		XMLManager xml2 = new XMLManager(node);
		System.out.println(xml2.getString("."));
		
	}
}
