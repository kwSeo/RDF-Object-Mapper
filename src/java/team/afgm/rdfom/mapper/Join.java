package team.afgm.rdfom.mapper;

public class Join {
	private final String resultMapId;
	private final String field;
	
	public Join(String resultMapId, String field){
		this.resultMapId = resultMapId;
		this.field = field;
	}
	
	public String getResultMapId(){
		return resultMapId;
	}
	
	public String getField(){
		return field;
	}
	
	@Override
	public String toString(){
		return String.format("resultMapId : %s, field : %s", resultMapId, field);
	}
}
