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

package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {
	public static final String TEXT_EMPTY = "";
	private String id;
	private String type;
	private boolean isDefault;
	private List<Property> properties;
	
	public Endpoint() {
		this(TEXT_EMPTY, TEXT_EMPTY, false, new ArrayList<>());
	}

	public Endpoint(String id, String type, boolean isDefault, List<Property> properties) {
		this.id = id;
		this.type = type;
		this.isDefault = isDefault;
		this.properties = properties;
	}

	public boolean isDefault(){
		return this.isDefault;
	}
	
	public void setDefault(boolean isDefault){
		this.isDefault = isDefault;
	}
	
	public Endpoint(boolean isDefault) {
		super();
		this.isDefault = isDefault;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;

	}

	public void setType(String type) {
		this.type = type;

	}

	public List<Property> getProperty() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
		
	}

}
