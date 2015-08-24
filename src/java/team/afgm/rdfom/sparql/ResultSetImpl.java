/*
RDF-Object Mapping Library is a Java library 
for mapping SPARQL results to Java object(JavaBean, POJO etc.).
Copyright (C) 2015  KyeongWon Seo(kwSeo), JuHyeon Moon(jhMoon)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package team.afgm.rdfom.sparql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElementDecl;

/**
 * 
 * @author kwSeo
 *
 */
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

	@Override
	public Object getValue(String column) {
		return rows.get(index).getValue(column);
	}

	
}
