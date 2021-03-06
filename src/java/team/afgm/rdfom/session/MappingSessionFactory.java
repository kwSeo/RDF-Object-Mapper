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

package team.afgm.rdfom.session;

import java.util.Map;

import team.afgm.rdfom.context.ContextConfig;
import team.afgm.rdfom.context.Endpoint;
import team.afgm.rdfom.endpoint.EndpointProcessor;
import team.afgm.rdfom.endpoint.FileEndpointProcessor;
import team.afgm.rdfom.endpoint.URLEndpointProcessor;
import team.afgm.rdfom.mapper.MapperConfig;

/**
 * Builder로부터 생성된 MappingSessionFactory는 파싱된 설정파일들을 가지고 있다. 그렇기 때문에 Factory로 MappingSession을 생성하여도 XML File IO 작업이 일어나지 않는다.
 * 원래 Builder가 없고 설정파일들이 생성될때 캐시를 이용할려고 했는데 Factory위에 Builder를 하나 더 둬서 지금과 같은 방법으로 MappingSession 생성시 IO 작업이 일어나지 않도록 하였다.
 * 설정파일들은 Builder를 통해 생성되지만 EndpointProcesser는 Factory에서 생성된다. 현재 EndpointProcesser는 두 종류를 생각하고 있으며 각각 File과 URL을 통해 쿼리를 수행하는 프로세서이다.
 * @author kwSeo
 *
 */
public class MappingSessionFactory {
	public static final String ENDPOINT_TYPE_FILE = "file";
	public static final String ENDPOINT_TYPE_URL = "url";
	
	private ContextConfig contextConfig;
	private Map<String, MapperConfig> mapperConfigs;
	
	
	public MappingSessionFactory(ContextConfig contextConfig, Map<String, MapperConfig> mapperConfig){
		this.contextConfig = contextConfig;
		this.mapperConfigs = mapperConfig;
	}
	
	public MappingSession createMappingSession(String endpointId){
		EndpointProcessor processer;
		Endpoint endpoint = contextConfig.findEndPoint(endpointId);
		String type = endpoint.getType().trim().toLowerCase();
		switch(type){
		case ENDPOINT_TYPE_FILE:
			processer = new FileEndpointProcessor(endpoint.getProperty().get(0).getValue());
			break;
		case ENDPOINT_TYPE_URL:
			processer = new URLEndpointProcessor(endpoint.getProperty().get(0).getValue());
			break;
		default:
			throw new RuntimeException("Error Endpoint Type.");
		
		}
		MappingSessionImpl mappingSession = new MappingSessionImpl(contextConfig, mapperConfigs, processer);
		return mappingSession;
	}
	
	
}