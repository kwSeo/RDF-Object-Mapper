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

package team.afgm.rdfom.endpoint;

import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import team.afgm.rdfom.endpoint.exception.QueryExecutionException;
import team.afgm.rdfom.endpoint.exception.XMLParserException;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.ResultSetFactory;

/**
 * 사용자가 준비한 RDF 파일로부터 SPARQL 쿼리를 수행한다.
 *  
 * @author kwSeo
 *
 */
public class FileEndpointProcessor extends EndpointProcessor{
	private String path;
	private Model model;

	public FileEndpointProcessor(String path) {
		this.path = path;
		InputStream is = FileManager.get().open(path);
		this.model = ModelFactory.createDefaultModel();
		this.model.read(is, null);
	}

	public FileEndpointProcessor(String path, Model model){
		this.path = path;
		this.model = model;
	}
	
	/**
	 * SPARQL SELECT 쿼리를 실행하고 그 결과 XML값을 문자열로 반환한다.
	 * 
	 * @param sparql
	 * @return String
	 */
	public String executeSelectToString(String sparql) {
		Query query = QueryFactory.create(sparql);

		try (QueryExecution exec = QueryExecutionFactory.create(query, model)) {
			com.hp.hpl.jena.query.ResultSet resultSet = exec.execSelect();
			String xmlStr = ResultSetFormatter.asXMLString(resultSet);
			
			return xmlStr;

		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new QueryExecutionException("Error executing SELECT.");
		}

	}
	
	/**
	 * SPARQL SELECT 쿼리를 실행하고 그 결과 XML을 Document 객체로 반환한다.
	 * 
	 * @param sparql
	 * @return Document
	 */
	@Override
	public ResultSet executeSelect(String sparql) {
		try{
			String xmlStr = executeSelectToString(sparql);
			return ResultSetFactory.getInstance().createJAXBResultSet(xmlStr);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new XMLParserException("Error parsing SPARQL result XML. " + e.getMessage());
		}
	}

	
	/**
	 * @param sparql
	 * @return boolean
	 * 	true or false
	 */
	@Override
	public boolean executeAsk(String sparql) {
		Query query = QueryFactory.create(sparql);
		
		try(QueryExecution exec = QueryExecutionFactory.create(query, model)){
			return exec.execAsk();
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new QueryExecutionException("Error executing ASK");
		}
	}
	

	/**
	 * SPARQL CONSTRUCT 쿼리로 생성된 RDF를 Jena 프레임워크의 Model 객체로 반환한다.
	 * 
	 * @param sparql
	 * @return Model
	 * 	Jena Framework Model 
	 */
	@Override
	public Model executeConstruct(String sparql) {
		Query query = QueryFactory.create(sparql);
		
		try(QueryExecution exec = QueryExecutionFactory.create(query, model)){
			return exec.execConstruct();
		}catch(Exception e){
			throw new QueryExecutionException("Error executing CONSTRUCT.");
		}
	}

	/**
	 * 파일경로 반환.
	 * @return String
	 */
	public String getPath() {
		return this.path;
	}
}
