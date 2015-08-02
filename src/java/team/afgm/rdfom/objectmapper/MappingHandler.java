package team.afgm.rdfom.objectmapper;

/**
 * MappingHandler 인터페이스는 ObjectMapper를 위한 인터페이스로, SPARQL 결과값의 컬럼명과 클래스의 필드명을 매핑시켜주는 역할을 한다.  
 * 사용자는 이 인터페이스를 구현하는 클래스를 구현하여 ObjectMapper에 등록해 자신이 원하는데로 컬럼-필드 규칙을 정할 수 있다.
 * ObjectMapper는 생성시 기본적으로 DefaultMappingHandler를 가지게 되며 이 헨들러는 컬럼명을 그대로 필드명으로 사용한다.
 * @author kwSeo
 *
 */
public interface MappingHandler {
	/**
	 * 
	 * @param columnName
	 * 		SPARQL 결과값의 컬럼명
	 * @return String.
	 * 		컬럼명에 매핑될 필드명
	 */
	public String convert(String columnName);
}
