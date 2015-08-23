package team.afgm.rdfom.objectmapper;

import java.util.HashMap;
import java.util.Map;

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
