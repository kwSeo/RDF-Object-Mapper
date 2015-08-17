package team.afgm.rdfom.objectmapper;

import java.util.List;

import team.afgm.rdfom.sparql.ResultSet;
import team.afgm.rdfom.util.StringUtil;

/**
 * 기본 매핑 헨들러는 인자로 전달받은 컬럼명을 그대로 반환한다.
 * 즉 클래스의 필드명과 SPARQL 쿼리 결과의 컬럼명이 동일하면 매핑한다.
 * @author kwSeo
 *
 */
public class DefaultMappingHandler implements MappingHandler{
	private List<String> columnName;
	
	public DefaultMappingHandler(ResultSet resultSet){
		this.columnName = resultSet.getColumns();
	}
	
	@Override
	public String convert(String columnName){
		return StringUtil.toCamelCaseSimple(columnName);
	}

	@Override
	public boolean hasChild() {
		return false;
	}

	@Override
	public List<MappingHandler> getChildMappingHandlers() {
		return null;
	}

	@Override
	public List<String> getColumns() {
		return columnName;
	}

	@Override
	public List<String> getFields() {
		return columnName;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String getId() {
		return null;
	}
	
	
	
}