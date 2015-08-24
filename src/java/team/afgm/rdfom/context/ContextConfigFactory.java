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

import java.io.InputStream;

import team.afgm.rdfom.xml.parser.XMLManager;

public class ContextConfigFactory {
	private static ContextConfigFactory factory;
	static {
		factory = new ContextConfigFactory();
	}
	
	private ContextConfigFactory() {}
	
	public static ContextConfigFactory getInstance() {
		return factory;
	}
	
	public ContextConfig createContextConfig(String contextPath) {
		XMLManager xml = new XMLManager(contextPath);
		return new ContextConfigImpl(
					EndpointsParser.parse(xml),
					NamespacesParser.parse(xml),
					MapperResourcesParser.parse(xml),
					AliasesParser.parse(xml),
					CacheElementParser.parse(xml)
				);
	}
	
	public ContextConfig createContextConfig(InputStream inputStream){
		XMLManager xml = new XMLManager(inputStream);
		return new ContextConfigImpl(
					EndpointsParser.parse(xml),
					NamespacesParser.parse(xml),
					MapperResourcesParser.parse(xml),
					AliasesParser.parse(xml),
					CacheElementParser.parse(xml)
				);
	}

}
