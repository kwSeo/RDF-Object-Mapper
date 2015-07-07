package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import team.afgm.rdfom.xml.parser.XMLManager;

public class ResultMapFactory {
	private static ResultMapFactory factory = new ResultMapFactory();
	
	private ResultMapFactory(){}
	
	public static ResultMapFactory getInstance(){
		return factory;
	}
	
	
	public List<ResultMap> createResultMapsByXML(String mapperPath){
		List<ResultMap> list = new ArrayList<>();
		XMLManager xml = new XMLManager(mapperPath);
		int numOfResultMap = xml.getInteger("count(//resultMap)");
		
		for(int i=1 ; i<=numOfResultMap ; i++){
			ResultMap resultMap = new ResultMapImpl();
			String expr = "//resultMap[" + i + "]";
			resultMap.setId(xml.getString(expr + "/@id"));
			resultMap.setType(xml.getString(expr + "/@type"));
			resultMap.setResults(getResults(xml, i));
			list.add(resultMap);
		}
		
		return list;
	}
	
	protected List<Result> getResults(XMLManager xml, int index){
		List<Result> results = new ArrayList<>();
		String expr = "//resultMap[" + index +"]/result";
		int numOfResult = xml.getInteger("count(" + expr + ")");
		
		for(int i=1 ; i<=numOfResult ; i++){
			Result result = new Result();
			String subExpr = expr + "[" + i + "]";
			result.setColumn(xml.getString(subExpr + "/@column"));
			result.setField(xml.getString(subExpr + "/@field"));
			results.add(result);
		}
		
		return results;
		
	}
	/**
	 * 아직 미구현...
	 * @param doc
	 * @return null
	 */
	public List<ResultMap> createResultMaps(Document doc){
		return null;
	}
}
