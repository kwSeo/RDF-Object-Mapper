package team.afgm.rdfom.mapper;

/**
 * 
 * @author kwSeo
 *
 */
public interface Select extends Comparable<Select>{
	public String getId();
	public String getResultType();	
	public String getQuery();
	public void setId(String id);
	public void setResultType(String resultType);
	public void setQuery(String query);
}
