package team.afgm.rdfom.endpoint;

import team.afgm.rdfom.sparql.ResultSet;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * SPARQL 쿼리를 수행하여 알맞은 갑슬 반환한다.
 * SPARQL의 SELECT와 ASK는 반환값에 대한 XML, JSON 등의 포맷표준이 존재한다.
 * 
 * @author kwSeo
 *
 */
public abstract class EndpointProcessor {
	/**
	 * 파라미터로 전달하는 SPARQL를 통해 쿼리를 실행한다.
	 * SELECT는 W3C에서 정의한 SPARQL의 결과값의 포맷표준이 존재한다.
	 * 표준포맷에는 XML, JSON 등이 존재하며, 본 메서드는 XML포맷을 사용한다.
	 * 
	 * @param sparql
	 * @return ResultSet
	 *   반환되는 값은 org.w3c.dom.Document 객체이다.
	 */
	public abstract ResultSet executeSelect(String sparql);
	
	/**
	 * 파라미터로 전달하는 SPARQL을 통해 쿼리를 실행한다.
	 * ASK도 W3C에 정의한 표준포맷이 존재하지만 결과값은 true 또는 false 뿐이기 때문에 boolean타입의 값을 반환한다.
	 * 
	 * @param sparql
	 * @return boolean
	 * 	true or false
	 */
	public abstract boolean executeAsk(String sparql);
	
	/**
	 * 파라미터로 전달하는 SPARQL을 통해 쿼리를 싱행한다.
	 * CONSTRUCT의 결과값은 표준포맷이 존재하지 않는다.
	 * 애초에 새로운 RDF를 생성하는 것이니 표준포맷이 필요없다.
	 * 새로운 RDF를 생성하는 것이기에 Jena 프레임워크의 Model 객체를 반환한다.
	 * 
	 * @param sparql
	 * @return Model
	 *   Jena Framework Model
	 */
	public abstract Model executeConstruct(String sparql);
	
	
}
