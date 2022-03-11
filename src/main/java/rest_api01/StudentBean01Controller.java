package rest_api01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean01Controller {

		@GetMapping(path="/getRequest")
		//it tells spring boot that this method will be used in request
		@ResponseBody //To be able to see the message on the console
		public String getMethod1() {
			return "Get Request method is executed...";
			
			//Using @RestController and @GetMapping(path="/getRequest") is better because it's shorter
			//In this method we returned just a String 
		}
		@GetMapping(path="/getObject")
		public StudentBean01 getMethod2() {
			return new StudentBean01("Ali Can", 13, "AC202113");
		}

		//The previous method is tight coupling, to make it loose coupling type the following method
		@Autowired//this annotation means that you assigned the object(bean) to the s object
		StudentBean01 s;
		
		@GetMapping(path="/getXXX")
		public StudentBean01 getMethod3() {
			s.setAge(13);
			s.setName("Ali Can");
			s.setId("AC202113");
			return s;
		}
		
		//To return a parameterized object you should create this method:
		
		@GetMapping(path="getParameterized/{school}")
		public StudentBean01 getMethod4(@PathVariable String school) {
			return new StudentBean01("Ali Can", 13, String.format("AC%s202113", school));
			
			//be careful about path="/getParameterized/{school}" and getMethod4(@PathVariable String school)
			//this is tight coupling 
		}
		//to make the method loose coupling:
		@Autowired
		StudentBean01 s2;
		
		
		@GetMapping(path="getParameterized2/{school}")
		public StudentBean01 getMethod5(@PathVariable String school) {
			s2.setAge(13);
			s2.setName("Ali Can");
			s2.setId(String.format("AC%s202113", school));
			return s2;
		}
		
		//How to return Object List
		@GetMapping(path="/getList")
		public List<StudentBean01> getMethod6(){
			return List.of(
							new StudentBean01("AB", 12, "AB12"),
							new StudentBean01("CD", 13, "CD13"),
							new StudentBean01("EF", 14, "EF14")
					);
		}
		
		//For this method, we will learn loose coupling in Repository Layer
		
		//If there is not path then it is home page
		@GetMapping
		public String getMethod7() {
			return "Get Request for home page...";
		}
		
		//You cannot use same path inside the @GetMapping paranthesis it won't work

		/*
		 	Because there are 2 beans in IoC, Java cannot decide to select
		 	Because of that we use @Qualifier(value="studentBean01") if we want to select bean of "StudentBean01"
		 	If you use @Qualifier(value="studentBean02"), it means you selected bean of "StudentBean02"
		 	Default name of beans in IoC is same with the class name whse intial is lower case
		 */
		
		@Autowired
		@Qualifier(value="studentBean01")
		StudentInterface std;
		@GetMapping(path="/getMessage")
		public String getMethod8() {
			return std.study();
		}

		
		
		
}
