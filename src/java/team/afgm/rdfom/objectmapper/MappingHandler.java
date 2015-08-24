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
import java.util.Set;

/**
 * MappingHandler 인터페이스는 ObjectMapper를 위한 인터페이스로, SPARQL 결과값의 컬럼명과 클래스의 필드명을 매핑시켜주는 역할을 한다.  
 * 사용자는 이 인터페이스를 구현하는 클래스를 구현하여 ObjectMapper에 등록해 자신이 원하는데로 컬럼-필드 규칙을 정할 수 있다.
 * ObjectMapper는 생성시 기본적으로 DefaultMappingHandler를 가지게 되며 이 헨들러는 컬럼명을 그대로 필드명으로 사용한다.
 * @author kwSeo
 *
 */
public interface MappingHandler {
	/**
	 * 
	 * @param columnName
	 * 		SPARQL 결과값의 컬럼명
	 * @return String.
	 * 		컬럼명에 매핑될 필드명
	 */
	public String convert(String columnName);
	
	public boolean hasChild();
	
	public List<MappingHandler> getChildMappingHandlers();
	
	public List<String> getColumns();
	
	public List<String> getFields();
	
	public String getType();
	
	public String getId();
}
