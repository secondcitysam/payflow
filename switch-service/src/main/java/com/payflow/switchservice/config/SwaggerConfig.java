package com.payflow.switchservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("PayFlow Switch Service")
                                .version("1.0")
                                .description(
                                        "Central payment routing service"
                                )
                );
    }
}