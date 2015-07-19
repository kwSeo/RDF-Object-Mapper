package team.afgm.util.collection.test;

import java.util.List;

import org.junit.Test;

import team.afgm.util.collection.BTree;

public class BTreeTest {
	@Test
	public void addAndPrintTest(){
		BTree<TestValue> bTree = new BTree<>();
		bTree.add(new TestValue(10));
		bTree.add(new TestValue(20));
		bTree.add(new TestValue(30));
		bTree.add(new TestValue(5));
		bTree.add(new TestValue(25));
		bTree.add(new TestValue(30));
		bTree.add(new TestValue(100));
		
		List<TestValue> values = bTree.getSortedValues();
		for(TestValue value : values){
			System.out.print(value.toString() + ", ");
		}
		System.out.println();
		
		TestValue person = new TestValue(30);
		TestValue findPerson = bTree.get(person);
		System.out.println(findPerson);
	}
}

class TestValue implements Comparable<TestValue>{
	private int value;
	
	public TestValue(int value){
		this.value = value;
	}
	
	@Override
	public int compareTo(TestValue o) {
		return this.value - o.value;
	}
	
	@Override
	public String toString(){
		return String.valueOf(value);
	}
	
}
