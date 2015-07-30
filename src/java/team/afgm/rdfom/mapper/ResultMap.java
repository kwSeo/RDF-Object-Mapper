package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author kwSeo
 *
 */
public class ResultMap implements Comparable<ResultMap> {

	private String id;
	private String type;
	private List<Result> results;
	
	public ResultMap(){
		this.id = "";
		this.type = "";
		this.results = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public int compareTo(ResultMap o) {
		return this.id.compareTo(o.getId());
	}
}
