package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.objectmapper.exception.ObjectMapperException;
import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.util.StringUtil;

/**
 * TODO 메서드의 파라미터로 ResultSet을 쓰는게 과연 옳은가?
 * @author kwSeo
 *
 */
public class ObjectMapper {
	private MappingHandler handler = new DefaultMappingHandler();
	
	public void setMappingHandler(MappingHandler handler){
		this.handler = handler;
	}
	
	public <T> T readValue(ResultSet resultSet, Class<T> classType){
		T instance = newInstance(classType);
		
		if(resultSet.next()){
			setupInstance(instance, resultSet, classType);
		
			resultSet.beforeFirst();	//커서를 초기화한다.
			return instance;
			
		}else{
			return null;				//resultSet에 Row가 없다면 null반환.
		}
	}
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType){
		List<T> resultList = new ArrayList<>();
		
		try{
			while(resultSet.next()){
				T instance = newInstance(classType);
				setupInstance(instance, resultSet, classType);
				
				resultList.add(instance);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error mapping object with SPARQL ResultSet.");
		}
		
		resultSet.beforeFirst();
		return resultList;
	}
	
	protected <T> T newInstance(Class<T> classType){
		T instance;
		
		try{
			instance = classType.newInstance();
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error creating instance.");
		}
		
		return instance;
	}
	
	/**
	 * 인자로 전달한 instance에 resultSet의 현재 row의 속성(column) 값들을 매핑하고 반환한다. 
	 * 인자로 전달한 instance와 반환하는 instance는 동일한 객체이다.
	 * @param instance
	 * @param resultSet
	 * @param classType. instance의 타입.
	 * @return T. 인자로 전달한 instance가 반환된다.
	 */
	protected <T> T setupInstance(T instance, ResultSet resultSet, Class<T> classType) {
		try{
			List<String> columnNames = resultSet.getColumns();
			
			for(String column : columnNames){
				//TODO 값을 문자열로만 받아오고 있다. 개선이 필요.
				Object value = resultSet.getValue(column);
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