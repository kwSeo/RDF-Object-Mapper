package team.afgm.rdfom.mapper;

/**
 * 
 * @author kwSeo
 *
 */
public interface Select {
	public String getId();
	public String getResultType();	
	public String getQuery();
	public void setResultType(String resultType);
	public void setQuery(String query);
}
