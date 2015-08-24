/*
RDF-Object Mapping Library is a Java library 
for mapping SPARQL results to Java object(JavaBean, POJO etc.).
Copyright (C) 2015  KyeongWon Seo(kwSeo), JuHyeon Moon(jhMoon)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package team.afgm.rdfom.objectmapper;

import java.util.List;

import team.afgm.rdfom.sparql.ResultSet;

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
		return columnName;
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