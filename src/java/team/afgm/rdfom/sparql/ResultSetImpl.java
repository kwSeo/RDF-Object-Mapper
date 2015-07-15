package team.afgm.rdfom.sparql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultSetImpl implements ResultSet{
	private List<Row> rows = new ArrayList<>();
	private int index = -1;
	
	public void addRow(Row row){
		rows.add(row);
		
	}
	
	public void addRow(String column, Object value){
		Row row = new Row();
		row.setValue(column, value);
		rows.add(row);
	}
	
	@Override
	public String getString(String name) {
		return rows.get(index).getStringValue(name);
	}

	@Override
	public int getInt(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean next() {
		if(index == rows.size()-1)
			return false;
		
		index++;
		return true;
	}

	@Override
	public void first() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void last() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void numOfRows() {
		// TODO Auto-generated method stub
		
	}

	
}


class Row{
	private Map<String, Object> map = new HashMap<>();
	
	public String getStringValue(String name){
		return String.valueOf(map.get(name));
	}
	
	public void setValue(String columnName, Object value){
		map.put(columnName, value);
	}
}
