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

import com.hp.hpl.jena.sparql.function.library.namespace;

public class Namespaces{
	
	private List<Namespace> namespaces;

	public Namespaces() {
		this.namespaces = new ArrayList<Namespace>();
	}

	public Namespaces(List<Namespace> namespaces){
		this.namespaces = namespaces;
	}
	
	public List<Namespace> getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(List<Namespace> namespaces) {
		this.namespaces = namespaces;
	}
	
	public Namespace getNamespace(int index){
		return namespaces.get(index);
	}
}
