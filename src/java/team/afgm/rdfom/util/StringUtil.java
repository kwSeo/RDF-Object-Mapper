package team.afgm.rdfom.util;

import java.lang.reflect.Method;

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
			Method[] methods = classType.getMethods();
			
			for(Method method : methods){
				if(method.getName().matches("^get(.)*") && !method.getName().equals("getClass")){
					Object value = method.invoke(obj);
					sb.append(method.getName()).append(" : ")
						.append(value).append(", ");
				}
			}
			
			String str = sb.toString();
			return str.substring(0, str.length()-2);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			return "";
		}
		
	}
}
