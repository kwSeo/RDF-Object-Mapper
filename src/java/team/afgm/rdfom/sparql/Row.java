package team.afgm.rdfom.sparql;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public class Row{
	private Map<String, Object> map = new HashMap<>();
	
	public String getStringValue(String name){
		return String.valueOf(map.get(name));
	}
	
	public Object getValue(String name){
		return map.get(name);
	}
	
	public void setValue(String columnName, Object value){
		map.put(columnName, value);
	}
}