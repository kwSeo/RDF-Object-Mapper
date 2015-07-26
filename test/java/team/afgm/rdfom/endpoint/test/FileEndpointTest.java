package team.afgm.rdfom.endpoint.test;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;

import team.afgm.rdfom.endpoint.FileEndpointProcesser;
import team.afgm.rdfom.sparql.JAXBData;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.ResultSetFactory;

public class FileEndpointTest {
	@Test
	public void endpointTest() throws TransformerException{
		/*
		 * 준비된 test.rdf파일에서 SPARQL 쿼리를 수행한다.
		 * FileEndpointProcesser를 통해 쿼리 결과값을 XML Document 객체 또는 XML String으로 받을 수 있다.
		 */
		FileEndpointProcesser pro = new FileEndpointProcesser("sample/test.rdf");
		String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT distinct ?x ?o "
				+ "WHERE { "
				+ "?x vcard:N ?a. "
				+ "?a vcard:Family ?o. "
				+ "} LIMIT 10";
		ResultSet resultSet = pro.executeSelect(query);
		String output = pro.executeSelectToString(query);
		System.out.println(output);
		
//		Document 객체가 지니고 있는 XML을 문자열로 변환하는 코드 
//		TransformerFactory tf = TransformerFactory.newInstance();
//		Transformer tr = tf.newTransformer();
//		tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//		StringWriter writer = new StringWriter();
//		tr.transform(new DOMSource(doc), new StreamResult(writer));
//		String output = writer.getBuffer().toString();
//		System.out.println(output);
		
		/*
		 * JAXB 테스트용...
		 */
		try{
			JAXBContext con = JAXBContext.newInstance(JAXBData.class);
			Unmarshaller un = con.createUnmarshaller();
			Object obj = un.unmarshal(new StringReader(output));
			
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		/*
		 * 반환된 ResultSet 객체 테스트
		 */
		String[] xArr = {"http://somewhere/RebeccaSmith/", 
				"http://somewhere/MattJones/", 
				"http://somewhere/SarahJones/",
				"http://somewhere/JohnSmith/"};
		String[] oArr = {"Smith", "Jones", "Jones", "Smith"};
		
		List<String> xArrTest = new ArrayList<>();
		List<String> oArrTest = new ArrayList<>();
		while(resultSet.next()){
			xArrTest.add(resultSet.getString("x")); 
			oArrTest.add(resultSet.getString("o"));
		}
		
		assertArrayEquals(xArr, xArrTest.toArray());
		assertArrayEquals(oArr, oArrTest.toArray());
	}
}
