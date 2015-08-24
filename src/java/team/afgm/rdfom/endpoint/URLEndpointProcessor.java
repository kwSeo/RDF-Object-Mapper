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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.jena.riot.lang.LangRDFXML;
import org.apache.jena.riot.lang.LangTurtle;
import org.apache.jena.riot.lang.LangTurtleBase;
import org.w3c.dom.Document;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.ModelReader;

import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.sparql.ResultSetFactory;
import team.afgm.rdfom.xml.parser.XMLManager;


/**
 * 
 * @author jhMoon
 * 
 * */


public class URLEndpointProcessor extends EndpointProcessor {
	private HttpClient client;
	private String url;
		
	public URLEndpointProcessor() {

	}

	public URLEndpointProcessor(String url) {
		this.url = url;
	}

	@Override
	public ResultSet executeSelect(String sparql) {
		client = new DefaultHttpClient();
		String result = null;
		try {
			String sparqlParam = URLEncoder.encode(sparql, "UTF-8");
			HttpGet get = new HttpGet(url+"?query="+sparqlParam);
			HttpResponse response = client.execute(get);
			
			result = getBody(response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if( client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return ResultSetFactory.getInstance().createJAXBResultSet(result);
	}

	@Override
	public boolean executeAsk(String sparql) {
		client = new DefaultHttpClient();
		String result = null;
		
		try {
			String sparqlParam = URLEncoder.encode(sparql, "UTF-8");
			HttpGet get = new HttpGet(url+"?query="+sparqlParam);
			HttpResponse response = client.execute(get);
			
			result = getBody(response);
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			
		} finally {
			client.getConnectionManager().shutdown();
			
		}
		
		return Boolean.parseBoolean(result);
		
	}

	@Override
	public Model executeConstruct(String sparql) {
		client = new DefaultHttpClient();
		String result = null;
		Model model = null;
		try {
			String sparqlParam = URLEncoder.encode(sparql, "UTF-8");
			HttpGet get = new HttpGet(url+"?query="+sparqlParam);
			HttpResponse response = client.execute(get);
			
			result = getBody(response);
			model = ModelFactory.createDefaultModel();
			model.read(new ByteArrayInputStream(result.getBytes()), "", "Turtle");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if( client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return model;
	}
	
	protected String getBody(HttpResponse response) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = null;
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

}
