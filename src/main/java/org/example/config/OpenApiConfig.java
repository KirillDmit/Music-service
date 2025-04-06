package org.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Music Service API",
                version = "1.0",
                description = "Документация REST API для музыкального сервиса"
        )
)
public class OpenApiConfig {
    
}
