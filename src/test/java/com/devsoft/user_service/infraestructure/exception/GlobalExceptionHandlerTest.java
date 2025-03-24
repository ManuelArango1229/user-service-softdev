package com.devsoft.user_service.infraestructure.exception;

import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerUnitTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Debe manejar RolInvalidoErrorException con código 400")
    void testHandleRolInvalido() {
        String message = "Rol no válido: ADMINX";
        RolInvalidoErrorException ex = new RolInvalidoErrorException(message);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleRolInvalido(ex);

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD_REQUEST"),
                () -> assertTrue(response.getBody().containsKey("error")),
                () -> assertEquals(message, response.getBody().get("error"))
        );
    }

    @Test
    @DisplayName("Debe manejar PasswordErrorException con código 400")
    void testHandlePasswordError() {
        String message = "La contraseña no cumple con los requisitos";
        PasswordErrorException ex = new PasswordErrorException(message);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handlePasswordError(ex);

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD_REQUEST"),
                () -> assertTrue(response.getBody().containsKey("error")),
                () -> assertEquals(message, response.getBody().get("error"))
        );
    }

    @Test
    @DisplayName("Debe manejar UsuarioExisteErrorException con código 409")
    void testHandleUsuarioExiste() {
        String message = "El usuario ya existe con ese DNI";
        UsuarioExisteErrorException ex = new UsuarioExisteErrorException(message);

        ResponseEntity<Map<String, String>> response = exceptionHandler.handleUsuarioExiste(ex);

        assertAll(
                () -> assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Debe retornar 409 CONFLICT"),
                () -> assertTrue(response.getBody().containsKey("error")),
                () -> assertEquals(message, response.getBody().get("error"))
        );
    }
}
