package com.service.backoffice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.*;
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
public class BackofficeApplication {

	public static void main(String[] args) {
		run(BackofficeApplication.class, args);
	}

}
