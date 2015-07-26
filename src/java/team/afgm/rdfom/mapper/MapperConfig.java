package team.afgm.rdfom.mapper;

import team.afgm.util.collection.SearchList;

/**
 * 
 * @author kwSeo
 *
 */
public interface MapperConfig {
	public String getNamesapce();
	public Select findSelect(String id);
	public SearchList<Select> getSelectList();
	public ResultMap findResultMap(String id);
	public SearchList<ResultMap> getResultMapList();
	public void setNamesapce(String namespace);
	public void setSelect(Select select);
	public void setSelectList(SearchList<Select> list);
	public void setResultMapList(SearchList<ResultMap> resultTypeList);
	
}
