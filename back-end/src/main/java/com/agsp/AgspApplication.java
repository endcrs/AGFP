package com.agsp;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableAutoConfiguration
public class AgspApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgspApplication.class, args);
	}
	
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}


}
