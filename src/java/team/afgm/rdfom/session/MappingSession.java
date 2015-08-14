package team.afgm.rdfom.session;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public interface MappingSession {
	public <T> T selectOne(String id);
	public <T> T selectOne(String id, Object param);
	public <T> T selectOne(String id, Map<String, Object> paramMap);
	public <T> List<T> selectList(String id);
	public <T> List<T> selectList(String id, Object param);
	public <T> List<T> selectList(String id, Map<String, Object> paramMap);
	
	public boolean ask(String id);
	public boolean ask(String id, Object param);
	public boolean ask(String id, Map<String, Object> paramMap);
}