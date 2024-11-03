package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class DemoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}

// @SpringBootApplication
// @RestController
// public class DemoApplication extends SpringBootServletInitializer {
// 	@Override
// 	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
// 		// TODO Auto-generated method stub
// 		return builder.sources(DemoApplication.class);

// 	}

// 	public static void main(String[] args) {
// 		SpringApplication.run(DemoApplication.class, args);
// 	}

// 	@RequestMapping(value="/")
//      public String hello() {
//      return "Hello World from Tomcat";
//     }



// }
