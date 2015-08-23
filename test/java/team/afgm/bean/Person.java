package team.afgm.bean;

public class Person {
	private String name="Test N";
	private String family="Test F";
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
