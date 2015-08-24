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

import java.util.Map;

import team.afgm.rdfom.context.Namespace;

/**
 * 기존 JDBC의 PreparedStatement처럼 쿼리문을 관리하며 전달받은 파라미터(클래스 등)로 값을 채워준다.
 * setValue 메서드를 통해 값을 전달할 수 있다. 본 라이브러리에서 사용되는 SPARQL 쿼리문에는 #{name} 형식의 구문이 포함시킬 수 있는데
 * 이는 변수를 의미하며 setValue메서드에 의해 전달받은 값으로 바뀌게 된다.
 * 예로 들어 #{name}변수는  다음과 같은 방식으로 값을 넣을 수 있다.
 * 
 * <code>
 * SparqlStatement stmt = new SparqlStatement(SPARQL Query...)
 * stmt.setValue("name", "value");
 * </code>
 * 
 * 또한 다음과 같이 Map을 사용해 값을 전달할 수도 있다.
 * 
 * <code>
 * Map<String, Object> map = new HashMap<>();
 * map.put("name", "value");
 * map.put("key", "value2");
 * stmt.setValue(map);
 * </code>
 * 
 * 클래스를 파라미터로 전달하면 클래스가 가지고 있는 맴버필드의 명을 통해서 값이 대입된다.
 * 이때 해당하는 맴버필드의 setter 메서드가 구현되어 있어야한다.
 * 
 * <b>Class</b>
 * <code>
 * public class Person{
 *   private String name;
 *   private String key;
 *   
 *   //getter and setter...
 * }
 * </code>
 * <code>
 * Person person = new Person();
 * person.setName("value");
 * person.setKey("value2");
 * stmt.setValue(person);
 * </code>
 * 
 * @author kwSeo
 *
 */
public interface SparqlStatement {
	public void addNamespace(Namespace namespace);
	
	public <T> void setValue(T value);
	
	public void setValue(Map<String, ?> map);
	
	public void setValue(String key, String value);
	
	public void setValue(String key, int value);
	
	public void setValue(String key, double value);
	
	public String getQuery();
	
}
