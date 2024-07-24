package com.example.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CollectionsSpring1Application {

	public static void main(String[] args) {
		SpringApplication.run(CollectionsSpring1Application.class, args);
	}

}
