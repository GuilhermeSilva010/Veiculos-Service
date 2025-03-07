package com.tinnova.coder.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Veiculos API")
                        .description("API RESTful para gerenciar veiculos")
                        .version("v1.0")
                        .termsOfService("http://swagger.io/terms/")
                );
    }
}
