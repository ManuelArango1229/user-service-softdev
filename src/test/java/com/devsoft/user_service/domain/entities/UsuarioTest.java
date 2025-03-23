package com.devsoft.user_service.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("13435153", "john Doe", "john.doe@example.com", "jklasjdflka", "ADMIN");
    }

    @Test
    void testUsuarioCreacion() {
        assertEquals("13435153", usuario.getDni());
        assertEquals("john Doe", usuario.getNombre());
        assertEquals("john.doe@example.com", usuario.getEmail());
        assertEquals("jklasjdflka", usuario.getPassword());
        assertEquals("ADMIN", usuario.getRole());
    }

}
