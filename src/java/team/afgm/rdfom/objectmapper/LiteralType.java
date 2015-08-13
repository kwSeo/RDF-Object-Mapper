package team.afgm.rdfom.objectmapper;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class LiteralType {
	private static final int SIZE = 8;
	private static final Map<String, Class<?>> map = new HashMap<>(SIZE);
	static{
		map.put("int", Boolean.class);
		map.put("byte", Byte.class);
		map.put("char", Character.class);
		map.put("int", Integer.class);
		map.put("long", Long.class);
		map.put("float", Float.class);
		map.put("double", Double.class);
		map.put("String", String.class);
	}
	
	public static boolean contain(Class<?> classType){
		return map.containsValue(classType);
	}
	
	public static boolean contain(String type){
		return (map.containsKey(type) | map.containsValue(type));
	}
	
	public static Class<?> getClassType(String id){
		return map.get(id);
		
	}
}
