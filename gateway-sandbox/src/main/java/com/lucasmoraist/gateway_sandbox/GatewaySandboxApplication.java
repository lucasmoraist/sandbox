package com.lucasmoraist.gateway_sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GatewaySandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaySandboxApplication.class, args);
	}

}
