package com.devsoft.user_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
