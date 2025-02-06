package com.aldairgc.budget_dev;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Local server")})
@SpringBootApplication
public class BudgetDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetDevApplication.class, args);
	}

}
