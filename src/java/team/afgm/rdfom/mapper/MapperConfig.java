package team.afgm.rdfom.mapper;

import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface MapperConfig {
	public String getNamesapce();
	public Select getSelect(String id);
	public List<Select> getSelectList();
	public ResultMap getResultMap(String id);
	public List<ResultMap> getResultMapList();
	public void setNamesapce(String namespace);
	public void setSelect(Select select);
	public void setSelectList(List<Select> list);
	public void setResultMapList(List<ResultMap> resultTypeList);
	
}
