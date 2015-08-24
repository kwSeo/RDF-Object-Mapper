/*
RDF-Object Mapping Library is a Java library 
for mapping SPARQL results to Java object(JavaBean, POJO etc.).
Copyright (C) 2015  KyeongWon Seo(kwSeo), JuHyeon Moon(jhMoon)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package team.afgm.rdfom.xml.parser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import team.afgm.rdfom.xml.exception.XmlBuildException;

/**
 * XPath를 사용하는 XML 관리 클래스로 파싱과 데이터 읽기를 수행한다.
 * 전달받은 XML경로(절대경로, 상대경로)릁 통해서 XML문서를 파싱하고 이를 XPath를 이용해서 접근한다.
 * XPath 표현식을 사용하여 해당하는 값을 문자열(String), 숫자(Integer, Double), 논리값(Boolean) 등으로 얻을 수 있다.
 * 
 * @author kwSeo
 * @date 2015-07-05
 *
 */
public class XMLManager {
	private Node doc;
	private XPath xPath = XPathFactory.newInstance().newXPath();
	
	/**
	 * 전달받은 XML 경로(classpath)를 통해서 Document 객체를 생성한다. 그리고 함께 XPath객체를 생성한다.
	 * @param path - XML파일 경로
	 */
	public XMLManager(String path){
		this(ClassLoader.getSystemClassLoader().getResourceAsStream(path));
	}
	
	/**
	 * 인자로 전달받은 노드를 루트노드로 하여 XMLManager를 생성한다.
	 * @param rootNode - XML Node
	 */
	public XMLManager(Node rootNode){
		this(rootNode, XPathFactory.newInstance().newXPath());
	}
	
	/**
	 * 
	 * 인자로 전달받은 노드를 루트노드로하며 XPath를 사용하여 XMLManager를 생성한다.
	 * @param path - 사용안함. 아무거나 줘도 된다.
	 * @param rootNode - XML Node
	 * @param xPath
	 */
	@Deprecated
	public XMLManager(String path, Node rootNode, XPath xPath){
		this.doc = rootNode;
		this.xPath = xPath;
	}
	
	/**
	 * 인자로 전달받은 노드를 루트노드로하며 XPath를 사용하여 XMLManager를 생성한다.
	 * @param rootNode - XML Node
	 * @param xPath
	 */
	public XMLManager(Node rootNode, XPath xPath){
		this.doc = rootNode;
		this.xPath = xPath;
	}
	
	/**
	 * 인자로 전달한 입력스트림을 통해 노드를 생성하여 XMLManager 객체를 생성한다.
	 * XPath는 기본 XPath(XPathFactory.newInstance().newXPath())를 사용한다.
	 * @param inputStream - XML input stream
	 */
	public XMLManager(InputStream inputStream) {
		try {
			this.doc = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(inputStream);
			
		} catch (Exception e){
			e.printStackTrace(System.err);
			throw new XmlBuildException("Error parsing XML from input stream. " + e.getMessage());
			
		}
	}

	/**
	 * 인자로 전달한 XPath 표현식을 통해 노드를 얻는다.
	 * @param expr - XPath expression
	 * @return Node - XML Node
	 */
	public Node getNode(String expr){
		return (Node)evaluate(expr, doc, XPathConstants.NODE);
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
	
	@Override
	public String toString(){
		return doc.getTextContent();
	}
}
