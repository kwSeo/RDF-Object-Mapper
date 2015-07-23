package team.afgm.rdfom.mapper.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import team.afgm.rdfom.mapper.Result;
import team.afgm.rdfom.mapper.ResultMap;
import team.afgm.rdfom.mapper.ResultMapFactory;
import team.afgm.util.collection.SearchList;

public class ResultMapFactoryTest {
	@Test
	public void testFactory(){
		String[] str = {"name", "age", "tel"};
		String[] str2 = {"team.afgm.bean.Person", "personMap"};
		String[] fields = new String[3];
		String[] columns = new String[3];
		String[] mapValues = new String[2];
		
		ResultMapFactory factory = ResultMapFactory.getInstance();
		SearchList<ResultMap> resultMaps = factory.createResultMapsByXML("sample/mapper-sample.xml");
		
		for(ResultMap resultMap : resultMaps){	//mapper-sample.xml에는 resultMap이 하나 밖에 없어서 오직 한번만 반복한다.
			mapValues[0] = resultMap.getType();
			mapValues[1] = resultMap.getId();	//그래서 걍 하나의 배열에 넣어도 태스트하는데에는 지장이 없다.
			
			List<Result> results = resultMap.getResults();
			for(int i=0 ; i<fields.length ; i++){
				fields[i] = results.get(i).getField();
				columns[i] = results.get(i).getColumn();
			}
		}
		
		assertArrayEquals(str, fields);
		assertArrayEquals(str, columns);
		assertArrayEquals(str2, mapValues);
		
	}
}
