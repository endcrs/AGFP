package com.agsp.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {


	  //http://localhost:8083/agfp/swagger-ui/index.html
		@Bean
		GroupedOpenApi swagger() {
			return GroupedOpenApi.builder()
					.group("com.agsp")
					.packagesToScan("com.agsp.controller")
					.build();
		}
		
	    @Bean
	    OpenAPI openAPI() {
	        
	        Contact contact = new Contact() //
	                .name("Desenvolvedores") //
	                .url("https://github.com/endcrs/AGFP");
	        
	        Info info = new Info() //
	                .title("API do projeto AGFP") //
	                .version("0.0.1") //
	                .contact(contact) //
	                .description("API responsável pelo funcionamento do aplicativo AGFP.");
	        
	        return new OpenAPI() //
	                .info(info); //
	    }

}
