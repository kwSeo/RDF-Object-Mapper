package team.afgm.rdfom.sparql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.afgm.rdfom.context.Namespace;
import team.afgm.rdfom.util.StringUtil;

/**
 * SparqlStatement 인터페이스의 구현체
 * @author kwSeo
 *
 */
public class SparqlStatementImpl implements SparqlStatement{

	/**
	 * SparqlStatement 객체를 생성하는 내부 클래스
	 * @author kwSeo
	 *
	 */
	public static class SparqlStatementBuilder{
		public static SparqlStatement newInstance(String sparqlQuery){
			return new SparqlStatementImpl(sparqlQuery);
		}
		
		public static SparqlStatement newInstance(String sparqlQuery, List<Namespace> namespaces){
			return new SparqlStatementImpl(sparqlQuery, namespaces);
		}
	}
	
	/**
	 * 파라미터로 전달받은 원본 쿼리는 originalQuery에
	 * 변수가 치환된 쿼리는 query 필드에 저장.
	 */
	private String originalQuery;
	private String query;
	private List<Namespace> namespaces;
	
	/**
	 * private으로 선언되 외부에서 new 키워드로는 생성 불가
	 * SparqlStatementBuilder 클래스를 통해 객체 생성
	 * @param sparqlQuery
	 */
	private SparqlStatementImpl(String sparqlQuery){
		this(sparqlQuery, new ArrayList<>());
	}
	
	private SparqlStatementImpl(String sparqlQuery, List<Namespace> namespaces){
		this.originalQuery = this.query = sparqlQuery;
		this.namespaces = namespaces;
	}
	
	/**
	 * 전달받은 클래스의 setter 메서드를 호출 후 반환값을 사용
	 */
	@Override
	public <T> void setValue(T value) {
		Class<?> classInfo = value.getClass();
		Field[] fields = classInfo.getDeclaredFields();
		
		for(Field field : fields){
			String fieldName = field.getName();
			String methodName = "get" + StringUtil.toCamelCaseSimple(fieldName);
			
			try{
				Method method = classInfo.getMethod(methodName);
				Object returnValue = method.invoke(value);
				this.setValue(fieldName, returnValue);
				
			}catch(Exception e){
				continue;
			}
		}
		
	}

	@Override
	public void setValue(Map<String, ?> map) {
		for(String key : map.keySet()){
			setValue(key, map.get(key));
		}
	}

	@Override
	public void setValue(String key, int value) {
		
		query = query.replaceAll("#\\{" + key + "\\}", String.valueOf(value));
		
	}
	
	@Override
	public void setValue(String key, double value){
		query = query.replaceAll("#\\{" + key + "\\}", String.valueOf(value));
	}
	
	@Override
	public void setValue(String key, String value){
		query = query.replaceAll("#\\{" + key + "\\}", "\"" + value + "\"");
	}

	public <T> void setValue(String key, T value){
		if(value instanceof Integer){
			setValue(key, ((Integer)value).intValue());
		}else if(value instanceof Double){
			setValue(key, ((Double)value).doubleValue());
		}else{
			setValue(key, value.toString());
		}
	}
	
	@Override
	public String getQuery() {
		StringBuilder queryBuilder = new StringBuilder();
		for(Namespace namespace : namespaces){
			String prefixName = namespace.getPrefix();
			String url = namespace.getUrl();
			queryBuilder.append("PREFIX ")
				.append(prefixName).append(": <")
				.append(url).append(">\n");			//\n은 없어도 되지 않을까...
		}
		
		queryBuilder.append(query);
		return queryBuilder.toString();
	}
	
	public String getOriginalQuery(){
		return this.originalQuery;
	}

	@Override
	public void addNamespace(Namespace namespace) {
		this.namespaces.add(namespace);
	}

}
