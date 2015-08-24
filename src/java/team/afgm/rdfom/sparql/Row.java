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

package team.afgm.rdfom.sparql;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public class Row{
	private Map<String, Object> map = new HashMap<>();
	
	public String getStringValue(String name){
		return String.valueOf(map.get(name));
	}
	
	public Object getValue(String name){
		return map.get(name);
	}
	
	public void setValue(String columnName, Object value){
		map.put(columnName, value);
	}
}