package com.devsoft.user_service.infraestructure.database.h2.entity;

import com.devsoft.user_service.domain.value_objects.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        usuarioEntity = new UsuarioEntity(
                "12345678",
                "Juan Pérez",
                "juan.perez@example.com",
                "ClaveSegura123",
                Role.CLIENTE
        );
    }

    @Test
    @DisplayName("Debe crear correctamente un UsuarioEntity con los valores dados")
    void testUsuarioEntityCreacion() {
        assertAll(
                () -> assertNull(usuarioEntity.getId(), "El ID debe ser nulo al momento de la creación"),
                () -> assertEquals("12345678", usuarioEntity.getDni(), "El DNI no es el esperado"),
                () -> assertEquals("Juan Pérez", usuarioEntity.getNombre(), "El nombre no es el esperado"),
                () -> assertEquals("juan.perez@example.com", usuarioEntity.getEmail(), "El email no es el esperado"),
                () -> assertEquals("ClaveSegura123", usuarioEntity.getPassword(), "La contraseña no es la esperada"),
                () -> assertEquals(Role.CLIENTE, usuarioEntity.getRol(), "El rol no es el esperado")
        );
    }

    @Test
    @DisplayName("Debe permitir modificar los atributos del UsuarioEntity")
    void testSetters() {
        usuarioEntity.setId(1L);
        usuarioEntity.setDni("87654321");
        usuarioEntity.setNombre("Laura Gómez");
        usuarioEntity.setEmail("laura.gomez@example.com");
        usuarioEntity.setPassword("NuevaClave456");
        usuarioEntity.setRol(Role.REPARTIDOR);

        assertAll(
                () -> assertEquals(1L, usuarioEntity.getId(), "El ID no se estableció correctamente"),
                () -> assertEquals("87654321", usuarioEntity.getDni(), "El DNI no se actualizó correctamente"),
                () -> assertEquals("Laura Gómez", usuarioEntity.getNombre(), "El nombre no se actualizó correctamente"),
                () -> assertEquals("laura.gomez@example.com", usuarioEntity.getEmail(), "El email no se actualizó correctamente"),
                () -> assertEquals("NuevaClave456", usuarioEntity.getPassword(), "La contraseña no se actualizó correctamente"),
                () -> assertEquals(Role.REPARTIDOR, usuarioEntity.getRol(), "El rol no se actualizó correctamente")
        );
    }
}
