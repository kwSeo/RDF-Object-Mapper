package team.afgm.rdfom.endpoint.test;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;

import team.afgm.rdfom.endpoint.FileEndpointProcesser;

public class EndpointFromFileTest {
	@Test
	public void endpointTest() throws TransformerException{
		FileEndpointProcesser pro = new FileEndpointProcesser("sample/test.rdf");
		String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
				+ "SELECT ?x "
				+ "WHERE { "
				+ "?x vcard:N ?o. "
				+ "} LIMIT 10";
		Document doc = pro.executeSelect(query);
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tr = tf.newTransformer();
		tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		tr.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString();
		System.out.println(output);
	}
}
