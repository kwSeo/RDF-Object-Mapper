package team.afgm.rdfom.mapper.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import team.afgm.rdfom.mapper.Select;
import team.afgm.rdfom.mapper.SelectFactory;
import team.afgm.util.collection.SearchList;

public class SelectFactoryTest {
	@Test
	public void createSelectsTest(){
		String[] str = {"testSelect", "testSelect2", "testSelect3"};
		String[] selectIds = new String[3];
		
		SearchList<Select> list = SelectFactory.getInstance().createSelectsByXML("sample/mapper-sample.xml");
		for(int i=0 ; i<list.size() ; i++){
			selectIds[i] = list.get(i).getId();
			System.out.println(list.get(i).getResultType());
			System.out.println(list.get(i).getQuery());
			
		}
		
		assertArrayEquals(str, selectIds);
		
	}
}
