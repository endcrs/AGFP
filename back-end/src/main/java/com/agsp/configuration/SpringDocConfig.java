package com.agsp.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	    //http://localhost:8081/agsp/swagger-ui/index.html
		@Bean
		GroupedOpenApi swagger() {
			return GroupedOpenApi.builder()
					.group("com.agsp")
					.packagesToScan("com.agsp.controller")
					.build();
		}

}
