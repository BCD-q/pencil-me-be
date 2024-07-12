package com.bcdq.pencilme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		servers = {
				@Server(url = "https://pencilme.inuappcenter.kr", description = "Server URL"),
				@Server(url = "http://localhost:8080",description = "Local URL")
		}
)
@SpringBootApplication
public class PencilmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PencilmeApplication.class, args);
	}

}
