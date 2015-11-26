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

package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.mapper.Join;
import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.objectmapper.exception.ObjectMapperException;
import team.afgm.rdfom.sparql.ResultSet;

/**
 * @author kwSeo
 *
 */
public class ObjectMapper {
	private final MapperConfig mapperConfig;
	
	public ObjectMapper(MapperConfig mapperConfig){
		this.mapperConfig = mapperConfig;
	}
	
	public <T> T readValue(ResultSet resultSet, Class<T> classType){
		return readValue(resultSet, classType, null);
	}
	
	public <T> T readValue(ResultSet resultSet, Class<T> classType, String resultMapId){
		List<T> resultList = readValueAsList(resultSet, classType, resultMapId);
		
		try{
			return resultList.get(0);
		}catch(Exception e){
			//0도 없다?(즉 결과값이 없다)
			return null;
		}
	}
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType){
		return this.readValueAsList(resultSet, classType, null);
	}
	
	
	public <T> List<T> readValueAsList(ResultSet resultSet, Class<T> classType, String resultMapId){
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
					T instance = newInstance(resultSet, classType, resultMapId);
					
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
	
	
	protected <T> T newInstance(ResultSet resultSet, Class<?> classType, String resultMapId){
		try{
			T instance = (T)classType.newInstance();

			if(resultMapId != null){
				ResultMap resultMap = mapperConfig.findResultMap(resultMapId);
				
				for(Result result : resultMap.getResults()){
					Object value = resultSet.getValue(result.getColumn());
					setValueToField(instance, result.getField(), value);
				}
				
				//join태그에 대해서 수행
				for(Join join : resultMap.getJoinList()){
					Field field = classType.getDeclaredField(join.getField());
					T subInstance = newInstance(
							resultSet, 
							field.getType(), 
							join.getResultMapId());				//재귀적 호출
					setValueToField(instance, join.getField(), subInstance);
				}
				
			}else{
				for(String name : resultSet.getColumns()){
					Object value = resultSet.getValue(name);
					setValueToField(instance, name, value);
				}
			}
			
			return instance;
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
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
	
	public void setValueToField(Object instance, String name, Object param) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		//종종 파라미터가 없는 경우가 있다. 예로들어 OPTIONAL을 사용하면 그렇다.
		if(param == null){
			return;
		}
		
		Class<?> classType = instance.getClass();
		try{
			Field field = classType.getDeclaredField(name);
			field.setAccessible(true);
			field.set(instance, param);
			field.setAccessible(false);
			
		}catch(NoSuchFieldException e){
			//해당하는 필드를 찾을 수 없으면 그냥 넘어간다.
			return;
		}

	}
}

