package team.afgm.rdfom.mapper;

import java.util.ArrayList;
import java.util.List;

import team.afgm.rdfom.xml.parser.XMLManager;

/**
 * @author kwSeo
 */
public class SelectFactory {
	private static SelectFactory factory = new SelectFactory();

	public static SelectFactory getInstance(){
		return factory;
	}
	
	public Select createDefaultSelect(){
		//SelectImpl의 기본 생성자는 모든 필드를 TEXT_EMPTY("")로 설정한다.
		return new SelectImpl();
	}

	public List<Select> createSelectsByXML(String mapperPath){
		List<Select> selectList = new ArrayList<>();
		
		XMLManager xml = new XMLManager(mapperPath);
		int numOfSelect = xml.getInteger("count(//select)");	//select 요소의 수를 반환
		
		for(int i=1 ; i<=numOfSelect ; i++){
			String expr = "//select[" + i + "]";
			String id = xml.getString(expr + "/@id");
			String resultType = xml.getString(expr + "/@resultType");
			String query = xml.getString(expr);
			
			selectList.add(
					new SelectImpl(id, resultType, query));
		}
		
		return selectList;
	}
}
