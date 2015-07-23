package team.afgm.rdfom.mapper;

import team.afgm.util.collection.SearchList;

/**
 * 
 * @author kwSeo
 *
 */
public class MapperConfigImpl implements MapperConfig{
	private String namespace;
	private SearchList<Select> selectList;
	private SearchList<ResultMap> resultMapList;
	
	public MapperConfigImpl(){
	}
	
	public MapperConfigImpl(String namespace, SearchList<Select> selectList, SearchList<ResultMap> resultMapList){
		this.namespace = namespace;
		this.selectList = selectList;
		this.resultMapList = resultMapList;
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
//BinaryTree로 변경되면서 코드바 바뀌었다. by kwSeo
//		Select select = SelectFactory.getInstance().createDefaultSelect();
//		select.setId(id);
//		int index = Collections.binarySearch(selectList, select);
		
		Select select = SelectFactory.getInstance().createDefaultSelect();
		select.setId(id);
		
		return selectList.search(select);
	}
	
	@Override
	public ResultMap findResultMap(String id) {
//BinaryTree로 변경되면서 코드바 바뀌었다. by kwSeo
//		ResultMap resultMap = ResultMapFactory.getInstance().createDefaultResultMap();
//		resultMap.setId(id);
//		int index = Collections.binarySearch(resultMapList, resultMap);
		
		ResultMap resultMap = ResultMapFactory.getInstance().createDefaultResultMap();
		resultMap.setId(id);
		return resultMapList.search(resultMap);
	}

	@Override
	public SearchList<Select> getSelectList() {
		return selectList;
	}

	@Override
	public SearchList<ResultMap> getResultMapList() {
		return resultMapList;
	}

	@Override
	public void setSelect(Select select) {
		selectList.add(select);
	}

	@Override
	public void setSelectList(SearchList<Select> list){
		this.selectList = list;
	}

	@Override
	public void setResultMapList(SearchList<ResultMap> resultTypeList) {
		this.resultMapList = resultTypeList;
	}
	
	

}
