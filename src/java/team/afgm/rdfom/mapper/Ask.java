package team.afgm.rdfom.mapper;

/**
 * 
 * @author kwSeo
 *
 */
public class Ask {
	public static final String TEXT_EMPTY = "";
	private String id;
	private String query;
	
	public Ask(){
		this(TEXT_EMPTY, TEXT_EMPTY);
	}
	
	public Ask(String id, String query){
		this.id = id;
		this.query = query;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
