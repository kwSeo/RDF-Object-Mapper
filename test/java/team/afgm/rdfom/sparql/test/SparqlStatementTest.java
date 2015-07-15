package team.afgm.rdfom.sparql.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import team.afgm.rdfom.sparql.Namespace;
import team.afgm.rdfom.sparql.SparqlStatement;
import team.afgm.rdfom.sparql.SparqlStatementImpl.SparqlStatementBuilder;

/**
 * SparqlStatement 테스트를 수행.
 * 변수치환과 네임스페이스를 통해 쿼리를 완성하는지 테스트한다.
 * 아직 ContextConfig 클래스가 완성되지 않아 Namespace부분은 임의로 만든 인터페이스로 테스트하고 있다.
 * @author kwSeo
 */
public class SparqlStatementTest {
	private final String query = "SELECT ?name WHERE { ?x a hc:Person; hc:age #{age}; hc:tel #{tel}; hc:name ?name. }";
	@Test
	public void stmtTest(){
		SparqlStatement stmt = SparqlStatementBuilder.newInstance(query);
		
		stmt.setValue("age", 25);
		stmt.setValue("tel", "010-1111-1111");
		assertEquals("SELECT ?name WHERE { ?x a hc:Person; hc:age 25; hc:tel \"010-1111-1111\"; hc:name ?name. }", stmt.getQuery());

		
		stmt = SparqlStatementBuilder.newInstance(query);
		Map<String, Object> map = new HashMap<>();
		map.put("age", 25);
		map.put("tel", "010-1111-1111");
		stmt.setValue(map);
		assertEquals("SELECT ?name WHERE { ?x a hc:Person; hc:age 25; hc:tel \"010-1111-1111\"; hc:name ?name. }", stmt.getQuery());
		
		stmt = SparqlStatementBuilder.newInstance(query);
		stmt.setValue(new Person());
		assertEquals("SELECT ?name WHERE { ?x a hc:Person; hc:age 25; hc:tel \"010-1111-1111\"; hc:name ?name. }", stmt.getQuery());
		
		//아직 ContextConfig 클래스가 완성되지 않아 Namespace부분은 임의로 만든 인터페이스로 테스트하고 있다.
		stmt = SparqlStatementBuilder.newInstance(query);
		Person person = new Person();
		Namespace namespace = new Namespace(){

			@Override
			public String getName() {
				return "hc";
			}

			@Override
			public String getUrl() {
				// TODO Auto-generated method stub
				return "http://www.test.co.kr#";
			}
		};
		stmt.addNamespace(namespace);
		stmt.setValue(person);
		System.out.println(stmt.getQuery());
		
	}
}
