package com.igor.cartaoIgor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("API do Cartão Igor")
                .description("API para gerenciamento de cartões de crédito")
                .version("v1")
            );
    }
    
}
