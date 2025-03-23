package com.devsoft.user_service.domain.value_objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValueObjectsTest {

    @Test
    void testEmailCreation() {
        Email email = new Email("test@example.com");
        assertEquals("test@example.com", email.getValue());
        assertEquals("test@example.com", email.toString());

        assertThrows(IllegalArgumentException.class, () -> new Email(null));

        assertThrows(IllegalArgumentException.class, () -> new Email(""));

        assertThrows(IllegalArgumentException.class, () -> new Email("invalid-email"));
    }

    @Test
    void testPasswordCreation() {
        Password password = new Password("Hola123HA");
        assertEquals("Hola123HA", password.getValue());
        assertEquals("********", password.toString());

        assertThrows(IllegalArgumentException.class, () -> new Password(null));

        assertThrows(IllegalArgumentException.class, () -> new Password(""));

        assertThrows(IllegalArgumentException.class, () -> new Password("hola123ha"));

        assertThrows(IllegalArgumentException.class, () -> new Password("HolaHola"));

        assertThrows(IllegalArgumentException.class, () -> new Password("Hola1"));

        password.setPassword("Nueva123HA");
        assertEquals("Nueva123HA", password.getValue());
    }

    @Test
    void testRoleFromString() {
        assertEquals(Role.CLIENTE, Role.fromString("CLIENTE"));
        assertEquals(Role.ADMINISTRADOR, Role.fromString("ADMINISTRADOR"));
        assertEquals(Role.REPARTIDOR, Role.fromString("REPARTIDOR"));

        assertEquals(Role.CLIENTE, Role.fromString("cliente"));
        assertEquals(Role.ADMINISTRADOR, Role.fromString("administrador"));
        assertEquals(Role.REPARTIDOR, Role.fromString("repartidor"));

        assertThrows(IllegalArgumentException.class, () -> Role.fromString(null));

        assertThrows(IllegalArgumentException.class, () -> Role.fromString(""));

        assertThrows(IllegalArgumentException.class, () -> Role.fromString("  "));

        assertThrows(IllegalArgumentException.class, () -> Role.fromString("INVALID_ROLE"));
    }
}