package team.afgm.rdfom.context;

public class Namespace {
	public String prefix;
	public String url;
	
	public Namespace(){
		this("", "");
	}
	
	public Namespace(String prefix, String url){
		this.prefix = prefix;
		this.url = url;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
