package com.devsoft.user_service.infraestructure.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para la documentación de la API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Función para especificar la información.
     * @return OpenAPI
     */
    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservicio Usuarios API")
                        .description("REST API para servicio de usuarios.")
                        .version("v0.0.1"));
    }
}

