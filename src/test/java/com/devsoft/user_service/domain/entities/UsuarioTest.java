package com.devsoft.user_service.domain.entities;

import com.devsoft.user_service.domain.value_objects.Email;
import com.devsoft.user_service.domain.value_objects.Password;
import com.devsoft.user_service.domain.value_objects.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
                () -> assertEquals("ADMINISTRADOR", usuario.getRole().name(), "El rol no es el esperado")
        );
    }

    @Test
    @DisplayName("Debe actualizar el DNI correctamente")
    void testSetDni() {
        usuario.setDni("98765432");
        assertEquals("98765432", usuario.getDni(), "El DNI no se actualizó correctamente");
    }

    @Test
    @DisplayName("Debe actualizar el nombre correctamente")
    void testSetNombre() {
        usuario.setNombre("Jane Doe");
        assertEquals("Jane Doe", usuario.getNombre(), "El nombre no se actualizó correctamente");
    }

    @Test
    @DisplayName("Debe actualizar el email correctamente")
    void testSetEmail() {
        Email nuevoEmail = new Email("jane.doe@example.com");
        usuario.setEmail(nuevoEmail);
        assertEquals("jane.doe@example.com", usuario.getEmail().getValue(), "El email no se actualizó correctamente");
    }

    @Test
    @DisplayName("Debe actualizar la contraseña correctamente")
    void testSetPassword() {
        usuario.setPassword("Nueva123HA");
        assertEquals("Nueva123HA", usuario.getPassword().getValue(), "La contraseña no se actualizó correctamente");
    }

    @Test
    @DisplayName("Debe actualizar el rol correctamente")
    void testSetRole() {
        usuario.setRole("REPARTIDOR");
        assertEquals("REPARTIDOR", usuario.getRole().name(), "El rol no se actualizó correctamente");
    }
}