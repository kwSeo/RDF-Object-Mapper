package team.afgm.rdfom.endpoint.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

import team.afgm.rdfom.endpoint.URLEndpointProcessor;

public class URLEndpointTest {
	@Test
	public void testURLendpoint() {
		URLEndpointProcessor urlpro = new URLEndpointProcessor("http://www.semantic-systems-biology.org/biogateway");
		String query = "ask {?x rdfs:label \"1700년\"@ko}";
		
		boolean resultBoolean = urlpro.executeAsk(query);
		assertFalse(resultBoolean);
	}
	
	@Test
	public void testURLendpoint2(){
		URLEndpointProcessor urlpro = new URLEndpointProcessor("http://ko.dbpedia.org/sparql");
		String query = "ask {?x rdfs:label \"1700년\"@ko}";
		
		boolean resultBoolean = urlpro.executeAsk(query);
		assertTrue(resultBoolean);
	}
	
	@Test
	public void testURLendpoint3(){
		URLEndpointProcessor urlpro = new URLEndpointProcessor("http://lod.openlinksw.com/sparql");
		String query = "ask {?x rdfs:label \"1700년\"@ko}";
		
		boolean resultBoolean = urlpro.executeAsk(query);
		assertTrue(resultBoolean);
	}
	
	@Test
	public void testURLendpoint4(){
		URLEndpointProcessor urlpro = new URLEndpointProcessor("http://lod.openlinksw.com/sparql");
		String query = "PREFIX db: <http://dbpedia.org/resource/>\n"
				+"PREFIX onto: <http://dbpedia.org/ontology/>\n"
				+"CONSTRUCT { db:France onto:anthem ?x }"
				+"WHERE { db:France onto:anthem ?x .}";
		
		Model resultBoolean = urlpro.executeConstruct(query);
	}
}
