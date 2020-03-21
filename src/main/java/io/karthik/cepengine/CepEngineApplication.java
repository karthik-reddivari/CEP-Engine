package io.karthik.cepengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegrationManagement;

@SpringBootApplication
@EnableIntegrationManagement
public class CepEngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(CepEngineApplication.class, args);
	}
}
