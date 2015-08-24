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

package team.afgm.rdfom.sparql;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @author kwSeo
 *
 */
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
		
		List<Variable> variables = data.getHead().getVariable();
		List<String> columnNames = new ArrayList<>();
		for(Variable variable : variables){
			columnNames.add(variable.getName());
		}
		
		resultSet.setColumnNames(columnNames);
		
		try{
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
			
		}catch(NullPointerException e){
			//결과값이 업사면 그냥 반환
			return resultSet;
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error parsing JAXBData to ResultSet. " + e.getMessage());
		}
		
	}
	
	public Object bind(Reader reader, Class<?> classType){
		try{
			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller un = context.createUnmarshaller();
			return un.unmarshal(reader);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error Unmarshaller.");
		}
	}
}
