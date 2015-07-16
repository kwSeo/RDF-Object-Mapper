package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.objectmapper.exception.ObjectMapperException;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.util.StringUtil;

public class ObjectMapper {
	public <T> T readValue(ResultSet resultSet, Class<T> classType){
		List<String> columnNames = resultSet.getColumns();
		T instance;
		
		try{
			instance = classType.newInstance();
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error creating instance.");
		}
		
		try{
			resultSet.first();
			for(String column : columnNames){
				Object value = resultSet.getString(column);
				Method method = classType.getMethod("set" + StringUtil.toCamelCaseSimple(column), value.getClass());
				
				method.invoke(instance, value);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error mapping object with SPARQL ResultSet.");
		}
		
		return instance;
	}
}
