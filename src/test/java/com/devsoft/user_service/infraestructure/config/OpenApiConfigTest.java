package com.devsoft.user_service.infraestructure.config;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

public class OpenApiConfigTest {

    @Test
    public void userServiceOpenAPI_shouldReturnValidOpenAPIInstance() {
        // Arrange
        OpenApiConfig config = new OpenApiConfig();

        // Act
        OpenAPI result = config.userServiceOpenAPI();

        // Assert
        assertNotNull(result);
        assertNotNull(result.getInfo());
        Info info = result.getInfo();
        assertEquals("Microservicio Usuarios API", info.getTitle());
        assertEquals("REST API para servicio de usuarios.", info.getDescription());
        assertEquals("v0.0.1", info.getVersion());
    }
}
