package team.afgm.rdfom.mappingsession.test;

public class Person {
	private String name;
	private String family;
	private int age=0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}

	
}
