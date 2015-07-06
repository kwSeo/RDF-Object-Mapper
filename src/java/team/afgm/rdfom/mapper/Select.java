package team.afgm.rdfom.mapper;

/**
 * 
 * @author kwSeo
 *
 */
public class Select {
	private String id;
	private String resultType;
	private String query;
	
	public Select(String id, String resultType, String query){
		this.id = id;
		this.resultType = resultType;
		this.query = query;
	}
	
	public String getId(){
		return id;
	}
	
	public String getResultType(){
		return resultType;
	}
	
	public String getQuery(){
		return query;
	}
	
	
}
