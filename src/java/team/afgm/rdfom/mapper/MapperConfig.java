package team.afgm.rdfom.mapper;

import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface MapperConfig {
	public String getNamespace();
	public Select findSelect(String id);
	public Ask findAsk(String id);
	public List<Select> getSelects();
	public ResultMap findResultMap(String id);
	public List<ResultMap> getResultMaps();
}
