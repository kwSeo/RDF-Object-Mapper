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

package team.afgm.rdfom.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author kwSeo
 *
 */
public class FIFOCache implements Cache{
	private String id;
	private int capacity;
	private Map<Object, Object> cacheMap;
	
	public FIFOCache(String id){
		this(id, DEFAULT_SIZE);
	}
	
	public FIFOCache(String id, int capacity){
		this.id = id;
		this.capacity = capacity;
		this.cacheMap = new LinkedHashMap<Object, Object>(capacity){
			@Override
			protected boolean removeEldestEntry(Map.Entry<Object,Object> eldest) {
		        return (this.size() > capacity);
		    }
		};
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		cacheMap.put(key, value);
	}

	@Override
	public Object getObject(Object key) {
		return cacheMap.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cacheMap.remove(key);
	}

	@Override
	public void clear() {
		cacheMap.clear();
	}

	@Override
	public int getSize() {
		return cacheMap.size();
	}

}
