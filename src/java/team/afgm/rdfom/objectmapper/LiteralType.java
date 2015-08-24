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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public class LiteralType {
	private static final int SIZE = 8;
	private static final Map<String, Class<?>> map = new HashMap<>(SIZE);
	static{
		map.put("boolean", Boolean.class);
		map.put("byte", Byte.class);
		map.put("char", Character.class);
		map.put("short", Short.class);
		map.put("int", Integer.class);
		map.put("long", Long.class);
		map.put("float", Float.class);
		map.put("double", Double.class);
		map.put("String", String.class);
		map.put("Boolean", Boolean.class);
		map.put("Byte", Byte.class);
		map.put("Character", Character.class);
		map.put("Short", Short.class);
		map.put("Integer", Integer.class);
		map.put("Long", Long.class);
		map.put("Float", Float.class);
		map.put("Double", Double.class);
	}
	
	public static boolean contain(Class<?> classType){
		return map.containsValue(classType);
	}
	
	public static boolean contain(String type){
		return map.containsKey(type);
	}
	
	public static Class<?> getClassType(String id){
		return map.get(id);
		
	}
}
