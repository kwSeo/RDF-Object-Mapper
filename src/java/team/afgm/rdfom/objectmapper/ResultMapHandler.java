package team.afgm.rdfom.objectmapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.mapper.Result;

/**
 * 
 * @author kwSeo
 *
 */
public class ResultMapHandler implements MappingHandler{
	private Map<String, String> map = new HashMap<>();
	
	public ResultMapHandler(List<Result> results){
//		Java8 문법을 써도 될까?
//		results.forEach((action)->{
//			map.put(action.getColumn(), action.getField());
//		});
		
		for(Result result : results){
			map.put(result.getColumn(), result.getField());
		}
	}
	
	@Override
	public String convert(String columnName) {
		return map.get(columnName);
	}
	
}