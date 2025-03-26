package com.devsoft.user_service.use_cases.exceptions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class exceptionsTest {
    
    @Test
    void testUsuarioNoEncontradoExceptionMessage() {
        // Definir el mensaje esperado
        String mensajeEsperado = "Usuario no encontrado con el DNI proporcionado";

        // Verificar que la excepción se lanza con el mensaje correcto
        assertThatThrownBy(() -> {
                    throw new UsuarioNoEncontradoException(mensajeEsperado);
                }).isInstanceOf(UsuarioNoEncontradoException.class)
                  .hasMessage(mensajeEsperado);
            }
            // Removed the unimplemented method as AssertJ provides the required functionality.
}
    
