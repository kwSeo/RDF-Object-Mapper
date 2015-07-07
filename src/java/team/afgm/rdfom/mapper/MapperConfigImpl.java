package team.afgm.rdfom.mapper;

import java.util.List;

import team.afgm.rdfom.mapper.exception.NotFoundIdException;

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
	public Select getSelect(String id) {
		for(Select select : selectList){
			if(select.getId().equals(id)){
				return select;
			}
		}
		
		//if not found, return null
		return null;
	}
	
	

	@Override
	public ResultMap getResultMap(String id) {
		for(ResultMap resultMap : resultMapList){
			if(resultMap.getId().equals(id))
				return resultMap;
		}

		//if not found, return null
		return null;
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
