package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Method;
import java.util.List;

import team.afgm.rdfom.objectmapper.exception.ObjectMapperException;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.util.StringUtil;

public class ObjectMapper {
	private MappingHandler handler = new DefaultMappingHandler();
	
	public void setMappingHandler(MappingHandler handler){
		this.handler = handler;
	}
	
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
				//TODO 값을 문자열로만 받아오고 있다. 개선이 필요.
				Object value = resultSet.getString(column);
				Method method = classType.getMethod(
								"set" + StringUtil.toCamelCaseSimple(
										handler.convert(column)), 
								value.getClass());
				
				method.invoke(instance, value);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error mapping object with SPARQL ResultSet.");
		}
		
		return instance;
	}
}

/**
 * 기본 매핑 헨들러는 인자로 전달받은 컬럼명을 그대로 반환한다.
 * 즉 클래스의 필드명과 SPARQL 쿼리 결과의 컬럼명이 동일하면 매핑한다.
 * @author kwSeo
 *
 */
class DefaultMappingHandler implements MappingHandler{
	@Override
	public String convert(String columnName){
		return columnName;
	}
}