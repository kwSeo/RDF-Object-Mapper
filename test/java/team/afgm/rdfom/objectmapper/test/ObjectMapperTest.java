package team.afgm.rdfom.objectmapper.test;

import org.junit.Test;
import org.w3c.dom.Document;

import team.afgm.rdfom.endpoint.FileEndpointProcesser;
import team.afgm.rdfom.objectmapper.ObjectMapper;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.ResultSetFactory;

public class ObjectMapperTest {
	@Test
	public void objectMapperTest(){
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
		Document doc = pro.executeSelect(query);
		String output = pro.executeSelectToString(query);
				
		ResultSet resultSet = ResultSetFactory.getInstance().createJAXBResultSet(output);
		
		ObjectMapper mapper = new ObjectMapper();
		TestBean instance = mapper.readValue(resultSet, TestBean.class);
		System.out.println(instance.toString());

	}
}
