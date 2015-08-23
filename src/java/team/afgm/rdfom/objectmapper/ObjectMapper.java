package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.objectmapper.exception.ObjectMapperException;
import team.afgm.rdfom.sparql.ResultSet;

/**
 * TODO 메서드의 파라미터로 ResultSet을 쓰는게 과연 옳은가?
 * @author kwSeo
 *
 */
public class ObjectMapper {
	
	public <T> T readValue(ResultSet resultSet, Class<T> classType){
		return readValue(resultSet, classType, new DefaultMappingHandler(resultSet));
	}
	
	public <T> T readValue(ResultSet resultSet, Class<T> classType, MappingHandler handler){
		List<T> resultList = readValueAsList(resultSet, classType, handler);
		
		try{
			return resultList.get(0);
		}catch(Exception e){
			//0도 없다?(즉 결과값이 없다)
			return null;
		}
	}
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType){
		return this.readValueAsList(resultSet, classType, new DefaultMappingHandler(resultSet));
	}
	
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType, MappingHandler handler){
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
					T instance = newInstance(resultSet, classType, handler);
					
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
	
	
	protected <T> T newInstance(ResultSet resultSet, Class<?> classType, MappingHandler handler){
		try{
			@SuppressWarnings("unchecked")
			T instance = (T)classType.newInstance();
			
			try{
				List<String> columnNames = handler.getColumns();
				
				for(String column : columnNames){
					Object value = resultSet.getValue(column);
					String memberName = handler.convert(column);
					//컬럴명에 해당하는 setter메서드를 찾음.
					//callSetter(instance, memberName, value);	<- setter 메서드를 이용하던 경우
					insertValueToField(instance, memberName, value);
				}
				
				if(handler.hasChild()){
					List<MappingHandler> childHandlers = handler.getChildMappingHandlers();
					for(MappingHandler childHandler : childHandlers){
						String nameId = childHandler.getId();
						Class<?> childClassType = Class.forName(childHandler.getType());
						Object childInstance = newInstance(resultSet, childClassType, childHandler);
						
						//callSetter(instance, StringUtil.toCamelCaseSimple(nameId), childInstance);	<- setter 메서드를 이용하던 경우
						insertValueToField(instance, nameId, childInstance);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace(System.out);
				throw new ObjectMapperException("Error mapping object with SPARQL ResultSet.");
			}
			
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
			throw new ObjectMapperException("Error creating literal instance. " + e.getMessage());
		}
	}
	
	public void callSetter(Object instance, String name, Object param) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> classType = instance.getClass();
		try{
			//종종 파라미터가 없는 경우가 있다. 예로들어 OPTIONAL 문법을 사용할 경우.
			Class<?> paramType = param.getClass();
		}catch(Exception e){
			return;
		}
		
		Method method = classType.getMethod(
				"set" + name,		//DefaultMappingHandler에 의해 컬럼명이 변경됨. 기본값은 맨 앞글자만 대분자로 바꿈.
				param.getClass());
		
		method.invoke(instance, param);							//메서드 호출. 파라미터 : 인스턴스, 파라미터
	}
	
	public void insertValueToField(Object instance, String name, Object param) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		//종종 파라미터가 없는 경우가 있다. 예로들어 OPTIONAL을 사용하면 그렇다.
		if(param == null){
			return;
		}
		
		Class<?> classType = instance.getClass();
		
		Field field = classType.getDeclaredField(name);
		field.setAccessible(true);
		field.set(instance, param);
		field.setAccessible(false);
	}
}

