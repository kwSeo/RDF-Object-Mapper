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

package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author kwSeo
 *
 */
public class ResultMap implements Comparable<ResultMap> {

	private String id;
	private String type;
	private List<Result> results;
	private List<ResultMap> childResultMap;
	
	public ResultMap(){
		this.id = "";
		this.type = "";
		this.results = new ArrayList<>();
		this.childResultMap = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public void setChildResultMap(List<ResultMap> childResultMap){
		this.childResultMap = childResultMap;
	}
	
	public List<ResultMap> getChildResultMap(){
		return childResultMap;
	}
	
	@Override
	public int compareTo(ResultMap o) {
		return this.id.compareTo(o.getId());
	}
}
