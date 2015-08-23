package team.afgm.rdfom.context;

import java.util.Map;

public class Aliases {
	
	private Map<String, Alias> aliasMap;
	
	public Aliases(Map<String, Alias> aliasMap) {
		super();
		this.aliasMap = aliasMap;
	}
	
	public Map<String, Alias> getAliasMap() {
		return aliasMap;
	}

	public void setAliasMap(Map<String, Alias> aliasMap) {
		this.aliasMap = aliasMap;
	}

	public Alias findAlias(String id) {
		return aliasMap.get(id);
	}
	
}
