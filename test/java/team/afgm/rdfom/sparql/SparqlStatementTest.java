package team.afgm.rdfom.sparql;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import team.afgm.rdfom.sparql.SparqlStatement;
import team.afgm.rdfom.sparql.SparqlStatementImpl.SparqlStatementBuilder;

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
	}
}
