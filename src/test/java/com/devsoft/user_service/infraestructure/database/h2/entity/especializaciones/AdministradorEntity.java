package com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones;

import static org.junit.jupiter.api.Assertions.*;

import com.devsoft.user_service.domain.value_objects.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdministradorEntityTest {

    @Test
    @DisplayName("Debe crear un AdministradorEntity con los valores correctos")
    void testCrearAdministradorEntity() {
        // Datos de prueba
        String dni = "12345678";
        String nombre = "Juan Pérez";
        String email = "juan.perez@example.com";
        String password = "passwordEncriptada";

        // Crear instancia
        AdministradorEntity administrador = new AdministradorEntity(dni, nombre, email, password);

        // Verificar que los valores son correctos
        assertNotNull(administrador);
        assertEquals(dni, administrador.getDni());
        assertEquals(nombre, administrador.getNombre());
        assertEquals(email, administrador.getEmail());
        assertEquals(password, administrador.getPassword());
        assertEquals(Role.ADMINISTRADOR, administrador.getRol());
    }

    @Test
    @DisplayName("Debe permitir crear una instancia vacía con el constructor sin argumentos")
    void testConstructorVacio() {
        AdministradorEntity administrador = new AdministradorEntity();
        assertNotNull(administrador);
    }
}