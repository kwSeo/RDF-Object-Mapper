package team.afgm.rdfom.endpoint;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import team.afgm.rdfom.sparql.ResultSet;

import com.hp.hpl.jena.rdf.model.Model;

public class URLEndpointProcessor extends EndpointProcessor {
	HttpClient client;
	String url;
	
	@Override
	public ResultSet executeSelect(String sparql) {
		client = new DefaultHttpClient();
		try {
			String sparqlParam = URLEncoder.encode(sparql, "UTF-8");
			HttpGet get = new HttpGet(url+"?query="+sparqlParam);
			HttpResponse response = client.execute(get);
			
		} catch(ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			if( client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return null;
	}

	@Override
	public boolean executeAsk(String sparql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Model executeConstruct(String sparql) {
		// TODO Auto-generated method stub
		return null;
	}

}
