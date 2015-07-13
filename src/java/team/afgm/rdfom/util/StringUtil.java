package team.afgm.rdfom.util;

import java.lang.reflect.Field;

public class StringUtil {
	/**
	 * 첫번째 문자만 대문자로
	 * @param str
	 * @return
	 */
	public static String toCamelCaseSimple(String str){
		String firstWord = str.substring(0,1).toUpperCase();
		return firstWord + str.substring(1, str.length());
	}
	
	public static <T> String toString(T obj){
		try{
			StringBuilder sb = new StringBuilder();
			
			Class classType = obj.getClass();
			Field[] fields = classType.getDeclaredFields();
			
			for(Field field : fields){
				String fieldName = field.getName();
				if(fieldName.equals("class"))
					continue;
				String getterName = "get" + toCamelCaseSimple(fieldName);
				Object returnValue = classType.getMethod(getterName).invoke(obj);
				sb.append(returnValue)
					.append(", ");
			}
			
			String str = sb.toString();
			return str.substring(0, str.length()-2);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			return "";
		}
		
	}
}
