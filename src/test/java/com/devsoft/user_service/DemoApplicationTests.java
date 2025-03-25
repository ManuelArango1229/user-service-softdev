package com.devsoft.user_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = DemoApplication.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class DemoApplicationTest {

    @Test
    @DisplayName("Debe cargar el contexto sin errores")
    void contextLoads() {
        // Este test asegura que el contexto arranca sin errores
    }

    @Test
    @DisplayName("Debe ejecutar el método main sin lanzar excepciones")
    void mainMethodRuns() {
        assertDoesNotThrow(() -> DemoApplication.main(new String[] {}));
    }
}
