package team.afgm.rdfom.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import team.afgm.rdfom.mapper.Select;
import team.afgm.rdfom.mapper.SelectFactory;

public class SelectFactoryTest {
	@Test
	public void createSelectsTest(){
		String[] str = {"testSelect", "testSelect2"};
		String[] selectIds = new String[2];
		
		List<Select> list = SelectFactory.getInstance().createSelectsByXML("sample/mapper-sample.xml");
		for(int i=0 ; i<list.size() ; i++){
			selectIds[i] = list.get(i).getId();
			System.out.println(list.get(i).getResultType());
			System.out.println(list.get(i).getQuery());
			
		}
		
		assertArrayEquals(str, selectIds);
		
	}
}
