package team.afgm.rdfom.objectmapper.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import team.afgm.rdfom.endpoint.FileEndpointProcessor;
import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.objectmapper.MappingHandler;
import team.afgm.rdfom.objectmapper.ObjectMapper;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.util.StringUtil;

public class ObjectMapperTest {
	private static final String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
			+ "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
			+ "SELECT distinct ?x ?o "
			+ "WHERE { "
			+ "?x vcard:N ?a. "
			+ "?a vcard:Family ?o. "
			+ "} LIMIT 10";
	
	@Test
	public void objectMapperTest(){
		/*
		 * 준비된 test.rdf파일에서 SPARQL 쿼리를 수행한다.
		 * FileEndpointProcesser를 통해 쿼리 결과값을 XML Document 객체 또는 XML String으로 받을 수 있다.
		 */
		FileEndpointProcessor pro = new FileEndpointProcessor("sample/test.rdf");

		ResultSet resultSet = pro.executeSelect(query);
				
		ObjectMapper mapper = new ObjectMapper();
		TestBean instance = mapper.readValue(resultSet, TestBean.class);
		System.out.println(instance.toString());

	}
	
	@Test
	public void objectMapperHandlerTest() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		List<Result> results = new ArrayList<>();
		Result result1 = new Result();
		result1.setColumn("x");
		result1.setField("name");
		results.add(result1);
		Result result2 = new Result();
		result2.setColumn("o");
		result2.setField("description");
		results.add(result2);
		
		mapper.setMappingHandler(new TestMappingHandler(results));
		FileEndpointProcessor pro = new FileEndpointProcessor("sample/test.rdf");
		ResultSet resultSet = pro.executeSelect(query);
		
		TestBean2 bean2 = mapper.readValue(resultSet, TestBean2.class);
		System.out.println(StringUtil.toString(bean2));
	}
	
	public static class TestMappingHandler implements MappingHandler{
		private Map<String, String> map = new HashMap<>();
		
		public TestMappingHandler(List<Result> results){
			results.forEach((action)->{
				map.put(action.getColumn(), action.getField());
			});
		}
		
		@Override
		public String convert(String columnName) {
			return StringUtil.toCamelCaseSimple(map.get(columnName));
		}
		
	}
}
