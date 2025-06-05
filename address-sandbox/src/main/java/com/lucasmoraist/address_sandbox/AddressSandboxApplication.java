package com.lucasmoraist.address_sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AddressSandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressSandboxApplication.class, args);
	}

}
