package team.afgm.rdfom.session;

import java.util.List;
import java.util.Map;

import team.afgm.rdfom.objectmapper.ObjectMapper;
import team.afgm.rdfom.sparql.SparqlStatement;

/**
 * 
 * @author kwSeo
 *
 */
public interface MappingSession {
	public <T> T selectOne(String id);
	public <T> T selectOne(String id, Object Param);
	public <T> T selectOne(String id, Map<String, Object> paramMap);
	public <T> List<T> selectList(String id);
	public <T> List<T> selectList(String id, Object param);
	public <T> List<T> selectList(String id, Map<String, Object> paramMap);
	
}