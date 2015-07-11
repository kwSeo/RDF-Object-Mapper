package team.afgm.rdfom.util;

public class StringUtil {
	/**
	 * 첫번째 문자만 대문자로
	 * @param str
	 * @return
	 */
	public static String toCamelCaseSimple(String str){
		String firstWord = str.substring(0,1).toUpperCase();
		return firstWord + str.substring(1, str.length());
	}
}
