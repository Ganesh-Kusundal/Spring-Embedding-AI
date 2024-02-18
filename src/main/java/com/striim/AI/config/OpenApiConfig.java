package com.striim.AI.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Embedding Services API",
                version = "1.0",
                description = "API for managing embedding services"
        ),
        servers = @Server(url = "/", description = "Default Server URL")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("*/**")
                .packagesToScan("com.striim.AI")
                .build();
    }
}
