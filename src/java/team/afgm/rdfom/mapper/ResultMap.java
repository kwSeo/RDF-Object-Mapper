package team.afgm.rdfom.mapper;

import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface ResultMap extends Comparable<ResultMap> {
	public String getId();
	public String getType();
	public List<Result> getResults();
	public void setId(String id);
	public void setType(String type);
	public void setResults(List<Result> results);
}
