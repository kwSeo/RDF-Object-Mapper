package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

public class MappersImpl implements Mappers {

	private List<Mapper> mappers;

	public MappersImpl() {
		this.mappers = new ArrayList<Mapper>();
	}

	@Override
	public List<Mapper> getMappers() {
		return mappers;
	}

	@Override
	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;

	}

}
