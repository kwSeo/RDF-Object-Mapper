package team.afgm.rdfom.mapper;

public class SelectImpl implements Select{
	public static final String TEXT_EMPTY = "";
	private String id;
	private String resultType;
	private String query;
	
	public SelectImpl(){
		this.id = this.resultType = this.query = TEXT_EMPTY;
	}
	
	public SelectImpl(String id, String resultType, String query){
		this.id = id;
		this.resultType = resultType;
		this.query = query;
	}
	
	@Override
	public String getId(){
		return id;
	}
	
	@Override
	public String getResultType(){
		return resultType;
	}
	
	@Override
	public String getQuery(){
		return query;
	}

	public void setId(String id){
		this.id = id;
	}
	
	@Override
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public int compareTo(Select o) {
		return this.id.compareTo(o.getId());
	}
	
	
}
