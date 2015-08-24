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

public class ContextConfigImpl implements ContextConfig {
	private Endpoints endpoints;
	private Namespaces namespaces;
	private MapperResources mapperResources;
	private Aliases aliases;
	private CacheElement cacheElement;
	

	public ContextConfigImpl(Endpoints endpoints, Namespaces namespaces, MapperResources mapperResources, Aliases aliases, CacheElement cacheElement) {
		this.endpoints = endpoints;
		this.namespaces = namespaces;
		this.mapperResources = mapperResources;
		this.aliases = aliases;
		this.cacheElement = cacheElement;
	}

	@Override
	public Endpoints getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Endpoints endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public Namespaces getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(Namespaces namespaces) {
		this.namespaces = namespaces;
	}

	@Override
	public MapperResources getMapperResources() {
		return mapperResources;
	}

	public void setMapperResources(MapperResources mapperResources) {
		this.mapperResources = mapperResources;
	}

	@Override
	public Aliases getAliases() {
		return aliases;
	}
	
	public void setAliases(Aliases aliases) {
		this.aliases = aliases;
	}

	@Override
	public Endpoint findEndPoint(String id) {
		return endpoints.findEndpoint(id);
	}

	@Override
	public Namespace findNamespace(int index) {
		return namespaces.getNamespace(index);
	}

	@Override
	public MapperResource findMapperResource(int index) {
		return mapperResources.getMapperResource(index);
	}

	@Override
	public Alias findAlias(String id) {
		return aliases.findAlias(id);
	}

	@Override
	public CacheElement getCache() {
		return cacheElement;
	}
	
	
}
