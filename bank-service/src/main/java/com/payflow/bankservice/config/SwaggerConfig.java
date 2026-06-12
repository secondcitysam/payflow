package com.payflow.bankservice.config;

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
                                .title("PayFlow Bank Service")
                                .version("1.0")
                                .description(
                                        "Account and Balance Management Service"
                                )
                );
    }
}