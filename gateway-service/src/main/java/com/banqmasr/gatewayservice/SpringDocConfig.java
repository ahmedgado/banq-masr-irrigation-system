package com.banqmasr.gatewayservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Gateway Service")
                        .version("1.0.0").description("Gateway is a " +
                                "service which is responsible for receive " +
                                "messages from all devices"));
    }
}
