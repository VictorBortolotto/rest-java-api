package com.example.restapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hotel API",
                version = "1.0",
                description = "API para gerenciamento de clientes, imóveis e reservas"
        )
)
public class OpenApiConfig {

}
