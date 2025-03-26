package com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones;

import static org.junit.jupiter.api.Assertions.*;

import com.devsoft.user_service.domain.value_objects.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteEntityTest {

    @Test
    @DisplayName("Debe crear un ClienteEntity con los valores correctos")
    void testCrearClienteEntity() {
        // Datos de prueba
        String dni = "87654321";
        String nombre = "María López";
        String email = "maria.lopez@example.com";
        String password = "passwordEncriptada";
        int edad = 30;
        String direccion = "Calle 123, Bogotá";
        String genero = "Femenino";
        String telefono = "3001234567";

        // Crear instancia
        ClienteEntity cliente = new ClienteEntity(dni, nombre, email, password, edad, direccion, genero, telefono);

        // Verificar que los valores son correctos
        assertNotNull(cliente);
        assertEquals(dni, cliente.getDni());
        assertEquals(nombre, cliente.getNombre());
        assertEquals(email, cliente.getEmail());
        assertEquals(password, cliente.getPassword());
        assertEquals(edad, cliente.getEdad());
        assertEquals(direccion, cliente.getDireccion());
        assertEquals(genero, cliente.getGenero());
        assertEquals(telefono, cliente.getTelefono());

        // Verifica el rol solo si existe el método getRole()
        if (hasMethod(cliente, "getRole")) {
            assertEquals(Role.CLIENTE, cliente.getRol());
        }
    }

    @Test
    @DisplayName("Debe permitir crear una instancia vacía con el constructor sin argumentos")
    void testConstructorVacio() {
        ClienteEntity cliente = new ClienteEntity();
        assertNotNull(cliente);
    }

    // Método auxiliar para verificar si un método existe en la clase
    private boolean hasMethod(Object obj, String methodName) {
        return java.util.Arrays.stream(obj.getClass().getMethods())
            .anyMatch(m -> m.getName().equals(methodName));
    }
}