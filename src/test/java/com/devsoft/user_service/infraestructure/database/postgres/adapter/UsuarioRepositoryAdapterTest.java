package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.postgres.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.postgres.repository.UsuarioJpaRepository;

public class UsuarioRepositoryAdapterTest {

    private UsuarioJpaRepository usuarioJpaRepository;
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @BeforeEach
    public void setUp() {
        usuarioJpaRepository = Mockito.mock(UsuarioJpaRepository.class);
        usuarioRepositoryPort = new UsuarioRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    public void save_shouldReturnSavedUsuario() {
        // Arrange
        Usuario usuario = new Usuario(
                "987654321",
                "Laura",
                "laura@example.com",
                "Password123*",
                "CLIENTE"
        );

        UsuarioEntity usuarioEntity = new UsuarioEntity(
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getPassword().getValue(),
                Role.CLIENTE
        );

        Mockito.when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Act
        Usuario result = usuarioRepositoryPort.save(usuario);

        // Assert
        assertEquals(usuario.getDni(), result.getDni());
        assertEquals(usuario.getNombre(), result.getNombre());
        assertEquals(usuario.getEmail().getValue(), result.getEmail().getValue());
        assertEquals(usuario.getPassword().getValue(), result.getPassword().getValue());
        assertEquals(usuario.getRole(), result.getRole());
    }

    @Test
    public void findByEmail_shouldReturnUsuarioIfExists() {
        // Arrange
        String email = "laura@example.com";
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                "987654321",
                "Laura",
                email,
                "Password123*",
                Role.CLIENTE
        );

        Mockito.when(usuarioJpaRepository.findByEmail(email)).thenReturn(Optional.of(usuarioEntity));

        // Act
        Optional<Usuario> result = usuarioRepositoryPort.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Laura", result.get().getNombre());
        assertEquals(email, result.get().getEmail().getValue());
    }

    @Test
    public void findByDni_shouldReturnUsuarioIfExists() {
        // Arrange
        String dni = "987654321";
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                dni,
                "Laura",
                "laura@example.com",
                "Password123*",
                Role.CLIENTE
        );

        Mockito.when(usuarioJpaRepository.findByDni(dni)).thenReturn(usuarioEntity);

        // Act
        Optional<Usuario> result = usuarioRepositoryPort.findByDni(dni);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni());
        assertEquals("Laura", result.get().getNombre());
        assertEquals("laura@example.com", result.get().getEmail().getValue());
    }
}
