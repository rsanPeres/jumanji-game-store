package com.jumanji.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WebGameStoreJumanjiApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebGameStoreJumanjiApplication.class, args);
	}

}
