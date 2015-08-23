package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public class MapperConfigImpl implements MapperConfig{
	private String namespace;
	private Map<String, ResultMap> resultMaps;
	private Map<String, Select> selects;
	private Map<String, Ask> asks;
	
	public MapperConfigImpl(){
	}

	/**
	 * 
	 * @param namespace
	 * @param resultMaps
	 * @param selects
	 * @param asks
	 */
	public MapperConfigImpl(String namespace, 
							Map<String, ResultMap> resultMaps, 
							Map<String, Select> selects,
							Map<String, Ask> asks) {
		super();
		this.namespace = namespace;
		this.resultMaps = resultMaps;
		this.selects = selects;
		this.asks = asks;
	}

	@Override
	public String getNamespace() {
		return this.namespace;
	}

	public void setNamesapce(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public Select findSelect(String id) {
		Select select = selects.get(id);
		
		return select;
	}
	
	@Override
	public ResultMap findResultMap(String id) {
		ResultMap resultMap = resultMaps.get(id);

		return resultMap;
	}

	@Override
	public List<Select> getSelects() {
		return new ArrayList<>(selects.values());
		
	}


	@Override
	public List<ResultMap> getResultMaps() {
		return new ArrayList<>(resultMaps.values());
	}

	@Override
	public Ask findAsk(String id) {
		return asks.get(id);
	}

	
	
}
