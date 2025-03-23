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
        usuario = new Usuario("13435153", "John Doe", "john.doe@example.com", "jklasjdflka", "ADMIN");
    }

    @Test
    @DisplayName("Debe crear un usuario con los valores correctos")
    void testUsuarioCreacion() {
        assertAll(
                () -> assertEquals("13435153", usuario.getDni(), "El DNI debería ser 13435153"),
                () -> assertEquals("John Doe", usuario.getNombre(), "El nombre debería ser John Doe"),
                () -> assertEquals("john.doe@example.com", usuario.getEmail(),
                        "El email debería ser john.doe@example.com"),
                () -> assertEquals("jklasjdflka", usuario.getPassword(), "La contraseña no coincide"),
                () -> assertEquals("ADMIN", usuario.getRole(), "El rol no es el esperado"));
    }

    @Test
    @Disabled("Este test esta diseñado para fallar")
    @DisplayName("Error intencional: El DNI es incorrecto")
    void testUsuarioCreacionConError() {
        assertAll(
                () -> assertNotEquals("13435153", usuario.getDni(), "Este test está diseñado para fallar"),
                () -> assertEquals("John Doe", usuario.getNombre(), "El nombre debería ser John Doe"),
                () -> assertEquals("john.doe@example.com", usuario.getEmail(),
                        "El email debería ser john.doe@example.com"),
                () -> assertEquals("jklasjdflka", usuario.getPassword(), "La contraseña no coincide"),
                () -> assertEquals("ADMIN", usuario.getRole(), "El rol no es el esperado"));
    }
}
