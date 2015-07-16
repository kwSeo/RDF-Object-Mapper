package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;

public class ResultMapImpl implements ResultMap {

	private String id;
	private String type;
	private List<Result> results;
	
	public ResultMapImpl(){
		this.id = "";
		this.type = "";
		this.results = new ArrayList<>();
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public List<Result> getResults() {
		return results;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public int compareTo(ResultMap o) {
		return this.id.compareTo(o.getId());
	}
	
	

}
