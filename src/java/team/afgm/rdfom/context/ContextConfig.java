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

public interface ContextConfig {
	
	public Endpoint findEndPoint(String id);
	
	public Namespace findNamespace(int index);
	
	public MapperResource findMapperResource(int index);

	public Endpoints getEndpoints();
	
	public Namespaces getNamespaces();
	
	public MapperResources getMapperResources();
	
	public Aliases getAliases();
	
	public Alias findAlias(String id);
	
	public CacheElement getCache();
}