package team.afgm.rdfom.xml.parser;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.exception.XmlBuildException;

/**
 * XPath를 사용하는 XML 관리 클래스로 파싱과 데이터 읽기를 수행한다.
 * 전달받은 XML경로(절대경로, 상대경로)릁 통해서 XML문서를 파싱하고 이를 XPath를 이용해서 접근한다.
 * XPath 표현식을 사용하여 해당하는 값을 문자열(String), 숫자(Integer, Double), 논리값(Boolean) 등으로 얻을 수 있다.
 * 
 * @author kwSeo(서경원)
 * @date 2015-07-05
 *
 */
public class XMLManager {
	private Document doc;
	private XPath xPath;
	private String path;
	
	/**
	 * 전달받은 XML 경로(path)를 통해서 Document 객체를 생성한다. 그리고 함께 XPath객체를 생성한다.
	 * @param path
	 */
	public XMLManager(String path){
		try{
			this.path = path;
			this.doc = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(path);	//InputSource에 대해서 좀 알아보자(InputSource는 상대경로가 안된다)
			this.xPath = XPathFactory.newInstance().newXPath();
			
		}catch(Exception e){
			throw new XmlBuildException("Error finding XML path.");
		}
	}
	
	/**
	 * XPath의 예외처리에 대한 번거로움을 해소하기 위한 메서드.
	 * 이 클래스에서는 xPath.evaluate() 메서드를 직접사용하지 않고 이 메서드를 사용한다.
	 * @param expr
	 * @param item
	 * @param returnType
	 * @return Object 객체. returnType에 따라 달라지기 때문에 이 메서드르 사용하여 객체를 반환받으면 캐스팅을 해서 사용할 것을 권장한다.
	 * 
	 */
	public Object evaluate(String expr, Object item, QName returnType){
		try{
			return xPath.evaluate(expr, item, returnType);
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new XmlBuildException("Error evaluating XPath");
		}
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 값을 문자열로 반환한다.
	 * @param expr
	 * @return String 객체
	 */
	public String getString(String expr){
		return (String)evaluate(expr, doc, XPathConstants.STRING);
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 값을 정수로 반환한다.
	 * @param expr
	 * @return Integer 객체
	 */
	public Integer getInteger(String expr){
		return Integer.valueOf(getString(expr));
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 값을 논리값으로 반환한다.
	 * @param expr
	 * @return Boolean 객체
	 */
	public Boolean getBoolean(String expr){
		return (Boolean)evaluate(expr, doc, XPathConstants.BOOLEAN);
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 값을 실수로 반환한다.
	 * @param expr
	 * @return Double 객체
	 */
	public Double getDouble(String expr){
		return (Double)evaluate(expr, doc, XPathConstants.STRING);
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 요소(element)를 노드리스트(NodeList)로 반환한다.
	 * @param expr
	 * @return NodeList 객체
	 */
	public NodeList getNodeList(String expr){
		return (NodeList)evaluate(expr, doc, XPathConstants.NODESET);
	}
	
	/**
	 * 전달받은 XPath 표현식에 해당하는 값을 Java Collections인 List<Node>로 반환한다.
	 * @param expr
	 * @return List<Node> 객체
	 */
	public List<Node> getNodes(String expr){
		List<Node> nodes = new ArrayList<>();
		NodeList nodeList = getNodeList(expr);
		int length = nodeList.getLength();
		
		for(int i=0 ; i<length ; i++){
			nodes.add(nodeList.item(i));
		}
		
		return nodes;
	}
	
	public String getPath(){
		return this.path;
	}
	
	@Override
	public String toString(){
		return doc.getTextContent();
	}
}
