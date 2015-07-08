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
	
	public String getId(){
		return id;
	}
	
	public String getResultType(){
		return resultType;
	}
	
	public String getQuery(){
		return query;
	}

	@Override
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
