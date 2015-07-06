package team.afgm.rdfom.mapper;

import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface MapperConfig {
	public Select getSelect(String id);
	public List<Select> getSelectList();
	public List<ResultMap> getResultTypeList();
}
