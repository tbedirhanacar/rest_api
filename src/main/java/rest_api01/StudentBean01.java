package rest_api01;

import org.springframework.stereotype.Component;

@Component
//By using that annotation you are telling spring boot to create an object from StudentBean01
public class StudentBean01 implements StudentInterface {//The object in IoC Container is called Bean
	
	private String name;
	private int age;
	private String id;
	public StudentBean01() {
	}
	public StudentBean01(String name, int age, String id) {
		this.name = name;
		this.age = age;
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String study() {
		return "Students must study 01";
	}
	@Override
	public String toString() {
		return "StudentBean01 [name=" + name + ", age=" + age + ", id=" + id + "]";
	}

	
	
	

}
