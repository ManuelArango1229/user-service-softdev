package com.devsoft.user_service.domain.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class ExceptionTest {

    @Test
    @DisplayName("Debe lanzar una excepción de contraseña incorrecta")
    void testPasswordErrorException() {
        String errorMessage = "Contraseña incorrecta";
        PasswordErrorException exception = new PasswordErrorException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Debe lanzar una excepción de rol no válido")
    void testRolInvalidoErrorException() {
        String errorMessage = "Rol no válido";
        RolInvalidoErrorException exception = new RolInvalidoErrorException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Debe lanzar una excepción de usuario no encontrado")
    void testUsuarioExisteErrorException() {
        String errorMessage = "El usuario ya existe";
        UsuarioExisteErrorException exception = new UsuarioExisteErrorException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());

        assertTrue(exception instanceof RuntimeException);
    }
}
