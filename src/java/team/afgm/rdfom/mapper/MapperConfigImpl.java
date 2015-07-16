package team.afgm.rdfom.mapper;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public class MapperConfigImpl implements MapperConfig{
	private String namespace;
	private List<Select> selectList;
	private List<ResultMap> resultMapList;
	
	public MapperConfigImpl(){
	}
	
	public MapperConfigImpl(String namespace, List<Select> selectList, List<ResultMap> resultMapList){
		this.namespace = namespace;
		this.selectList = selectList;
		Collections.sort(this.selectList);
		this.resultMapList = resultMapList;
		Collections.sort(this.selectList);
	}
	
	
	@Override
	public String getNamesapce() {
		return this.namespace;
	}

	@Override
	public void setNamesapce(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public Select findSelect(String id) {
		Select select = SelectFactory.getInstance().createDefaultSelect();
		select.setId(id);
		int index = Collections.binarySearch(selectList, select);
		return selectList.get(index);
	}
	
	@Override
	public ResultMap findResultMap(String id) {
		ResultMap resultMap = ResultMapFactory.getInstance().createDefaultResultMap();
		resultMap.setId(id);
		int index = Collections.binarySearch(resultMapList, resultMap);
		return resultMapList.get(index);
	}

	@Override
	public List<Select> getSelectList() {
		return selectList;
	}

	@Override
	public List<ResultMap> getResultMapList() {
		return resultMapList;
	}

	@Override
	public void setSelect(Select select) {
		selectList.add(select);
	}

	@Override
	public void setSelectList(List<Select> list){
		this.selectList = list;
	}

	@Override
	public void setResultMapList(List<ResultMap> resultTypeList) {
		this.resultMapList = resultTypeList;
	}
	
	

}
