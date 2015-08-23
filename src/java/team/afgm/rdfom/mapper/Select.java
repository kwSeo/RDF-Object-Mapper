package team.afgm.rdfom.mapper;
/**
 * 
 * @author kwSeo
 *
 */
public class Select {
	public static final String TEXT_EMPTY = "";
	private String id;
	private String resultType;
	private String query;
	
	public Select(){
		this.id = this.resultType = this.query = TEXT_EMPTY;
	}
	
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

	public void setId(String id){
		this.id = id;
	}
	
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int compareTo(Select o) {
		return this.id.compareTo(o.getId());
	}
	
	
}
