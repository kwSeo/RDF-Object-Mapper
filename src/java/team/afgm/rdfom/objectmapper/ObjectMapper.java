package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Constructor;
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
		List<T> resultList = readValueAsList(resultSet, classType);
		
		try{
			return resultList.get(0);
		}catch(Exception e){
			//0도 없다?(즉 결과값이 없다)
			return null;
		}
	}
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType){
		List<T> resultList = new ArrayList<>();
		
		try{
			//결과값의 타입이 리터럴 자료형일 경우(Integer, Double, String 등)
			if(LiteralType.contain(classType)){
				while(resultSet.next()){
					T instance = newLiteralInstance(resultSet, classType);
					
					resultList.add(instance);
				}
				
			//아닌 경우
			}else{
				while(resultSet.next()){
					T instance = newInstance(classType);
					setupInstance(instance, resultSet, classType);
					
					resultList.add(instance);
				}
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error mapping object with SPARQL ResultSet.");
		}
		
		resultSet.beforeFirst();
		return resultList;
	}
	
	protected <T> T newInstance(Class<T> classType){
		try{
			T instance;
			
			instance = classType.newInstance();
			
			return instance;
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error creating instance.");
		}
		
		
	}
	
	public <T> T newLiteralInstance(ResultSet resultSet, Class<T> classType){
		try{
			//결과값의 유형이 리터럴일 경우 결과값의 컬럼은 하나 뿐이다.
			String columnName = resultSet.getColumns().get(0);
			T instance;
			
			Constructor<T> constructor = classType.getConstructor(String.class);
			instance = constructor.newInstance(String.valueOf(resultSet.getValue(columnName)));
			
			return instance;
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new ObjectMapperException("Error creating literal instance.");
		}
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
				Object value = resultSet.getValue(column);
				
				//컬럴명에 해당하는 setter메서드를 찾음.
				Method method = classType.getMethod(
								"set" + handler.convert(column),		//DefaultMappingHandler에 의해 컬럼명이 변경됨. 기본값은 맨 앞글자만 대분자로 바꿈.
								value.getClass());
				
				method.invoke(instance, value);							//메서드 호출. 파라미터 : 인스턴스, 파라미터
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
		return StringUtil.toCamelCaseSimple(columnName);
	}
}