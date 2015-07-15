package team.afgm.rdfom.sparql;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ResultSetFactory {
	private static ResultSetFactory factory = new ResultSetFactory();
	
	private ResultSetFactory(){}
	
	public static ResultSetFactory getInstance(){
		return factory;
	}
	
	public ResultSet createJAXBResultSet(String sparqlResult){
		JAXBData data = (JAXBData)bind(new StringReader(sparqlResult), JAXBData.class);
		return createJAXBResultSet(data);
	}
	
	public ResultSet createJAXBResultSet(JAXBData data){
		ResultSetImpl resultSet = new ResultSetImpl();
		
		List<Result> results = data.getResults().getResult();
		for(Result result : results){
			Row row = new Row();
			List<Binding> bindings = result.getBinding();
			for(Binding binding : bindings){
				String col = binding.getName();
				Object value = binding.getValue();
				row.setValue(col, value);
			}
			resultSet.addRow(row);
		}
		
		return resultSet;
	}
	
	public Object bind(Reader reader, Class<?> classType){
		try{
			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller un = context.createUnmarshaller();
			return un.unmarshal(reader);
			
		}catch(Exception e){
			throw new RuntimeException("Error Unmarshaller.");
		}
	}
}
