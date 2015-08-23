package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

public class MapperResources {

	private List<MapperResource> mapperResourceList;

	public MapperResources(){
		this(new ArrayList<>());
	}
	
	public MapperResources(List<MapperResource> mapperResourceList){
		this.mapperResourceList = mapperResourceList;
	}

	public void setMapperResourceList(List<MapperResource> mapperResourceList){
		this.mapperResourceList = mapperResourceList;
	}
	
	public List<MapperResource> getMapperResourceList(){
		return mapperResourceList;
	}
	
	public MapperResource getMapperResource(int index){
		return mapperResourceList.get(index);
	}
}
