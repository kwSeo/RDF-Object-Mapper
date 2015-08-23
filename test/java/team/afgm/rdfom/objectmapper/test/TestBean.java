package team.afgm.rdfom.objectmapper.test;

public class TestBean {
	private String x;
	private String o;
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	
	@Override
	public String toString(){
		return x + "/" + o;
	}
}
