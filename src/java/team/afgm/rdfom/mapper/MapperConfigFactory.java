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

import java.io.InputStream;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * 
 * @author kwSeo
 *
 */
public class MapperConfigFactory {
	private static MapperConfigFactory factory;
	static{
		factory = new MapperConfigFactory();
	}
	
	private MapperConfigFactory(){}
	
	public static MapperConfigFactory get(){
		return factory;
	}
	
	public MapperConfig createMapperConfig(String mapperPath){
		XMLManager xml = new XMLManager(mapperPath);
		return createMapperConfig(xml);
	}
	
	public MapperConfig createMapperConfig(InputStream inputStream){
		XMLManager xml = new XMLManager(inputStream);
		return createMapperConfig(xml);
	}
	
	public MapperConfig createMapperConfig(XMLManager xml){
		MapperConfigImpl config = new MapperConfigImpl(
				xml.getString("//@namespace"),
				ResultMapParser.parse(xml),
				SelectParser.parse(xml),
				AskParser.parse(xml));
		
		return config;
	}
}
