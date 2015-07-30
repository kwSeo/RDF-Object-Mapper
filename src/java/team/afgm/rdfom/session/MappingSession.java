package team.afgm.rdfom.session;

import java.util.List;

public interface MappingSession {
	public <T> T selectOne(String id, Class<T> classType);
	public <T> List<T> selectList(String id, Class<T> classType);
}