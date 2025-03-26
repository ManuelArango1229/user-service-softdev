package com.devsoft.user_service.use_cases.exceptions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class exceptionsTest {
    
    @Test
    void testUsuarioNoEncontradoExceptionMessage() {
        String mensajeEsperado = "Usuario no encontrado con el DNI proporcionado";

        assertThatThrownBy(() -> {
                    throw new UsuarioNoEncontradoException(mensajeEsperado);
                }).isInstanceOf(UsuarioNoEncontradoException.class)
                  .hasMessage(mensajeEsperado);
            }
}
    
