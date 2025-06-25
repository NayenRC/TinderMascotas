package com.proyecto_byp.cl.proyecto_byp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Api para B&P")
            .version("1.1")
            .description("Con esta API se puede administrar el registro y match de mascotas")
        );
    }

}