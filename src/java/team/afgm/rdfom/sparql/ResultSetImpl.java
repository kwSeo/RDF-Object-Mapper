package team.afgm.rdfom.sparql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElementDecl;


public class ResultSetImpl implements ResultSet{
	private List<String> columnNames = new ArrayList<>();
	private List<Row> rows = new ArrayList<>();
	private int index = -1;
	
	public void setColumnNames(List<String> columnNames){
		this.columnNames = columnNames;
	}
	
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
		this.index = 1;
	}

	@Deprecated
	@Override
	public void last() {
		
	}

	@Override
	public void beforeFirst(){
		this.index = -1;
	}
	
	@Override
	public void numOfRows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getColumn(int index) {
		return columnNames.get(index);
	}

	@Override
	public List<String> getColumns() {
		return columnNames;
	}

	
}
