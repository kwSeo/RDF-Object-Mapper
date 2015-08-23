package team.afgm.rdfom.xml.exception;

public class XmlBuildException extends RuntimeException{

	public XmlBuildException(String message){
		super(message);
	}
	
	public XmlBuildException(String message, Throwable t){
		super(message, t);
	}
}
