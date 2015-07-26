package team.afgm.util.collection;

import java.util.List;

public interface SearchList<T> extends Iterable<T>{
	public void add(T newValue);
	public T search(T value);
	public List<T> getSortedValues();
	public int size();
	public T get(int index);
	
}
