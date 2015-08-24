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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.util.StringUtil;

/**
 * 
 * @author kwSeo
 *
 */
public class ResultMapHandler implements MappingHandler{
	private ResultMap resultMap;
	private Map<String, String> columnMap = new HashMap<>();
	
	public ResultMapHandler(List<Result> results){
		
		for(Result result : results){
			columnMap.put(result.getColumn(), result.getField());
		}
	}
	
	public ResultMapHandler(ResultMap resultMap){
		this(resultMap.getResults());
		this.resultMap = resultMap;
	}
	
	@Override
	public String convert(String columnName) {
		return columnMap.get(columnName);
	}

	@Override
	public boolean hasChild() {
		return !(resultMap != null && resultMap.getChildResultMap().isEmpty());
	}

	@Override
	public List<MappingHandler> getChildMappingHandlers() {
		List<ResultMap> childResultMaps = resultMap.getChildResultMap();
		List<MappingHandler> childHandlers = new ArrayList<>(childResultMaps.size());
		for(ResultMap childResultMap : childResultMaps){
			childHandlers.add(new ResultMapHandler(childResultMap));
		}
		return childHandlers;
	}

	@Override
	public List<String> getColumns() {
		return new ArrayList<>(columnMap.keySet());
	}

	@Override
	public List<String> getFields() {
		return new ArrayList<>(columnMap.values());
	}

	@Override
	public String getType() {
		return resultMap.getType();
	}
	
	@Override
	public String getId(){
		return resultMap.getId();
	}
	
}