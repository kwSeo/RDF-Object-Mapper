package team.afgm.rdfom.mapper.test;

import java.util.List;

import org.junit.Test;

import team.afgm.rdfom.mapper.MapperConfig;
import team.afgm.rdfom.mapper.MapperConfigFactory;
import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.mapper.Select;
import team.afgm.rdfom.util.StringUtil;

public class MapperConfigTest {
	@Test
	public void factoryTest(){
		MapperConfig config = MapperConfigFactory.get().createMapperConfig("sample/mapper-sample.xml");
		System.out.println(StringUtil.toString(config));
		Select select = config.findSelect("testSelect");
		ResultMap resultMap = config.findResultMap("personMap");
		
		System.out.println(StringUtil.toString(select));
		System.out.println(StringUtil.toString(resultMap));
		System.out.println("--------------------------------------------------------------------------");
		
		List<Select> selects = config.getSelects();
		for(Select s : selects){
			System.out.println(StringUtil.toString(s));
		}
		
		List<ResultMap> resultMaps = config.getResultMaps();
		for(ResultMap r : resultMaps){
			System.out.println(StringUtil.toString(r));
			for(Result result : resultMap.getResults())
				System.out.println(StringUtil.toString(result));
		}
		
	}
}
