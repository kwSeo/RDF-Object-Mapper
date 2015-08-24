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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** 
 * 
 * ResultSet 클래스는 SPARQL 쿼리 결과가 바인딩되는 클래스이다.<br>
 * 그래서 본 클래스는 SPARQL 결과값 표준에 맞게 설계되어 있으며 XML 결과값으로부터 바인딩된다.<br><br>
 * 참고 : <a href="http://www.w3.org/TR/rdf-sparql-XMLres/">SPARQL Query Results XML Format (Second Edition)</a>
 * 
 * @author kwSeo
 *
 */
@XmlRootElement(name="sparql", namespace="http://www.w3.org/2005/sparql-results#")
@XmlType(propOrder={"head", "results"})
public class JAXBData {
	private Head head;
	private Results results;
	
	public Head getHead() {
		return head;
	}

	@XmlElement(name="head")
	public void setHead(Head head) {
		this.head = head;
	}

	public Results getResults() {
		return results;
	}

	@XmlElement(name="results")
	public void setResults(Results results) {
		this.results = results;
	}
}

@XmlType(propOrder={"variable"})
class Head{
	private List<Variable> variable;

	public List<Variable> getVariable() {
		return variable;
	}

	public void setVariable(List<Variable> variable) {
		this.variable = variable;
	}
}

@XmlType(propOrder={"result"})
class Results{
	private List<Result> result;

	public List<Result> getResult() {
		return result;
	}
	
	@XmlElement(name="result")
	public void setResult(List<Result> result) {
		this.result = result;
	}
	
	
}

class Variable{
	
	private String name="";
	
	public String getName(){
		return this.name;
	}
	
	@XmlAttribute(name="name")
	public void setName(String name){
		this.name = name;
	}
	
	
}


@XmlType(propOrder={"binding"})
class Result{
	private List<Binding> binding;
	
	@XmlElement(name="binding")
	public void setBinding(List<Binding> binding){
		this.binding = binding;
	}
	
	public List<Binding> getBinding(){
		return binding;
	}
}


@XmlRootElement(name="binding")
@XmlType(propOrder={"uri", "bnode", "literal"})
class Binding{
	private String uri;
	private String bnode;
	private String name;
	private Literal literal;
	
	private String type;
	
	@XmlElement(name="uri")
	public void setUri(String uri){
		this.uri = uri;
		this.type = "uri";
	}
	
	@XmlElement(name="bnode")
	public void setBnode(String bnode){
		this.bnode = bnode;
		this.type = "bnode";
	}

	@XmlElement(name="literal")
	public void setLiteral(Literal literal){
		this.literal = literal;
		this.type = "literal";
	}
	
	@XmlAttribute(name="name")
	public void setName(String name){
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public String getBnode() {
		return bnode;
	}

	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	public Literal getLiteral(){
		return this.literal;
	}
	
	public Object getLiteralV(){
		String dataTypeArr[] = literal.getDatatype().split("[:#]");
		String dataType = dataTypeArr[dataTypeArr.length-1];			//데이터타입얻기. 마지막 부분이 데이터타입을 나타낸다.
		String literalValue = literal.getLiteral();		
		Object value = null;
		
		//Java7부터 되는 기능
		switch(dataType){
		case "integer": case "int":
			value = Integer.parseInt(literalValue);
			break;
		case "double":
			value = Double.parseDouble(literalValue);
			break;
		case "long":
			value = Long.parseLong(literalValue);
			break;
		case "byte":
			value = Byte.parseByte(literalValue);
			break;
		case "short":
			value = Short.parseShort(literalValue);
			break;
		case "float":
			value = Float.parseFloat(literalValue);
			break;
		case "boolean":
			value = Boolean.parseBoolean(literalValue);
			break;
		default:
			value = literalValue;
		}
		
		return value;
	}
	
	public Object getValue(){
		switch(type){
		case "uri":
			return getUri();
		case "bnode":
			return getBnode();
		case "literal":
			return getLiteralV();
		default:
			throw new RuntimeException("Error JAXBData in getValue. ");
		}
	}
}

@XmlRootElement(name="literal")
class Literal{
	private String literal;
	private String datatype = "xsd:string";
	
	public String getLiteral() {
		return literal;
	}
	
	@XmlValue
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	public String getDatatype() {
		return datatype;
	}
	
	@XmlAttribute(name="datatype")
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
}
