package com.diego.cleanArch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.diego.cleanArch")
public class CleanArchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanArchApplication.class, args);
	}

}
