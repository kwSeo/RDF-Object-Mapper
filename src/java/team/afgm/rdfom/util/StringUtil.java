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

package team.afgm.rdfom.util;

import java.lang.reflect.Method;

/**
 * 
 * @author kwSeo
 *
 */
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
