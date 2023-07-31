package com.abdulchakam.loginservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Simple my app", version = "1.0", description = "Simple App [Login Jwt token, CRUD User] RESTFul API",
				contact = @Contact(name = "Muhammad Abdul Chakam", email = "muhabdulchakam@gmail.com", url = "https://github.com/abdulchakam/coding-interview-v2")))
public class LoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServiceApplication.class, args);
	}

}
