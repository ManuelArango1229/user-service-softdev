package com.devsoft.user_service.infraestructure.database.postgres.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.devsoft.user_service.domain.value_objects.Role;

public class UsuarioEntityTest {

    @Test
    public void testConstructorYGetters() {
        // Arrange
        String dni = "123456789";
        String nombre = "Juan";
        String email = "juan@example.com";
        String password = "12345678Abc*";
        Role rol = Role.CLIENTE;

        // Act
        UsuarioEntity usuario = new UsuarioEntity(dni, nombre, email, password, rol);

        // Assert
        assertEquals(dni, usuario.getDni());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals(password, usuario.getPassword());
        assertEquals(rol, usuario.getRol());
    }

    @Test
    public void testUserDetailsMethods() {
        // Arrange
        UsuarioEntity usuario = new UsuarioEntity(
            "987654321",
            "Ana",
            "ana@example.com",
            "Password123*",
            Role.ADMINISTRADOR
        );

        // Act
        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        // Assert
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ADMINISTRADOR")));
        assertEquals("Password123*", usuario.getPassword());
        assertEquals("ana@example.com", usuario.getUsername());
    }

    @Test
    public void testConstructorVacio() {
        // Act
        UsuarioEntity usuario = new UsuarioEntity();

        // Assert
        assertNotNull(usuario);
    }
}
