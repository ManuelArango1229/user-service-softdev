package com.devsoft.user_service.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("13435153", "John Doe", "john.doe@example.com", "Hola123HA", "ADMINISTRADOR");
    }

    @Test
    @DisplayName("Debe crear un usuario con los valores correctos")
    void testUsuarioCreacion() {
        assertAll(
                () -> assertEquals("13435153", usuario.getDni(), "El DNI debería ser 13435153"),
                () -> assertEquals("John Doe", usuario.getNombre(), "El nombre debería ser John Doe"),
                () -> assertEquals("john.doe@example.com", usuario.getEmail().getValue(),
                        "El email debería ser john.doe@example.com"),
                () -> assertEquals("Hola123HA", usuario.getPassword().getValue(), "La contraseña no coincide"),
                () -> assertEquals("ADMINISTRADOR", usuario.getRole().name(), "El rol no es el esperado"));
    }
}
