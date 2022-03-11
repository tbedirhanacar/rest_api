package rest_api01;

import org.springframework.stereotype.Component;

@Component//To create an object in IoC
public class StudentBean02 implements StudentInterface{
	
	private String name;
	private int age;
	public StudentBean02() {
		System.out.println("Constructor without parameter is used");
	}
	public StudentBean02(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String study() {
		return "Students must study 02";
	}
	
	@Override
	public String toString() {
		return "StudentBean02 [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
