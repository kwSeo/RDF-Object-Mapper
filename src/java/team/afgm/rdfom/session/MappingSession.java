package team.afgm.rdfom.session;

import java.util.List;

public interface MappingSession {
	public <T> T selectOne(String id);
	public <T> List<T> selectList(String id);
}