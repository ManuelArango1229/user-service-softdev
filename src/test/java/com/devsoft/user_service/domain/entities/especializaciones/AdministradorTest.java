package com.devsoft.user_service.domain.entities.especializaciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdministradorTest {

    @Test
    @DisplayName("Debe crear un Administrador con los valores correctos")
    void testAdministradorCreation() {

        String dni = "12345678";
        String nombre = "Juan Admin";
        String emailEsperado = "admin@example.com";
        String password = "Securepassword123";
        
        Administrador administrador = new Administrador(dni, nombre, emailEsperado, password);
        
        assertEquals(dni, administrador.getDni());
        assertEquals(nombre, administrador.getNombre());
        assertNotNull(administrador.getPassword());
        assertFalse(administrador.getPassword().toString().isEmpty());

        assertEquals(emailEsperado, administrador.getEmail().toString());

        assertEquals("REPARTIDOR", administrador.getRole().toString());
    }
}
