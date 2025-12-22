package com.diego.cleanArch.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI(){
		return  new OpenAPI()
				.info(new Info()
				.title("Clean Architecture API")
				.version("1.0.0")
				.description("A RESTful API built with Clean Architecture principles, providing user management endpoints with complete CRUD operations.")
				.contact(new Contact()
						.name("Diego Godot")
						.email("diegogodoimartins@gmail.com")
						.url("https://github.com/Diego-godoi"))
				.license(new License()
						.name("MIT License")
						.url("https://opensource.org/licenses/MIT"))
				)
				.servers(Arrays.asList(
						new Server().url("http://localhost:8080").description("Development")
				));
	}
}
