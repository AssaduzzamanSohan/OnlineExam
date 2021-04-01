package com.example.onlineExam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineExamApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OnlineExamApplication.class);
	}

}
