package com.devsoft.user_service.infraestructure.database.h2.adapter;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.h2.repository.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioRepositoryAdapterTest {

    private UsuarioJpaRepository usuarioJpaRepository;
    private UsuarioRepositoryAdapter usuarioRepositoryAdapter;

    @BeforeEach
    void setUp() {
        usuarioJpaRepository = mock(UsuarioJpaRepository.class);
        usuarioRepositoryAdapter = new UsuarioRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    @DisplayName("Debe guardar un Usuario correctamente")
    void testSaveUsuario() {
        Usuario usuario = new Usuario("12345678", "Juan Pérez", "juan@example.com", "Clave123!", "CLIENTE");
        UsuarioEntity usuarioEntity = new UsuarioEntity("12345678", "Juan Pérez", "juan@example.com", "Clave123!", Role.CLIENTE);

        usuarioEntity.setId(1L);

        when(usuarioJpaRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario saved = usuarioRepositoryAdapter.save(usuario);

        assertAll(
                () -> assertEquals(usuario.getDni(), saved.getDni(), "El DNI no coincide"),
                () -> assertEquals(usuario.getNombre(), saved.getNombre(), "El nombre no coincide"),
                () -> assertEquals(usuario.getEmail().getValue(), saved.getEmail().getValue(), "El email no coincide"),
                () -> assertEquals(usuario.getPassword().getValue(), saved.getPassword().getValue(), "La contraseña no coincide"),
                () -> assertEquals(usuario.getRole().name(), saved.getRole().name(), "El rol no coincide")
        );
    }

    @Test
    @DisplayName("Debe encontrar un Usuario por DNI correctamente")
    void testFindByDni() {
        String dni = "87654321";
        UsuarioEntity usuarioEntity = new UsuarioEntity(dni, "Laura Gómez", "laura@example.com", "Segura456!", Role.REPARTIDOR);

        usuarioEntity.setId(2L);

        when(usuarioJpaRepository.findByDni(dni)).thenReturn(usuarioEntity);

        Optional<Usuario> result = usuarioRepositoryAdapter.findByDni(dni);

        assertAll(
                () -> assertTrue(result.isPresent(), "El usuario debería existir"),
                () -> assertEquals(dni, result.get().getDni(), "El DNI no coincide"),
                () -> assertEquals("Laura Gómez", result.get().getNombre(), "El nombre no coincide"),
                () -> assertEquals("laura@example.com", result.get().getEmail().getValue(), "El email no coincide"),
                () -> assertEquals("Segura456!", result.get().getPassword().getValue(), "La contraseña no coincide"),
                () -> assertEquals("REPARTIDOR", result.get().getRole().name(), "El rol no coincide")
        );
    }

    @Test
    @DisplayName("Debe retornar vacío si no encuentra usuario por DNI")
    void testFindByDniNotFound() {
        String dni = "00000000";
        when(usuarioJpaRepository.findByDni(dni)).thenReturn(null);

        Optional<Usuario> result = usuarioRepositoryAdapter.findByDni(dni);

        assertFalse(result.isPresent(), "No debería encontrar ningún usuario con ese DNI");
    }
}
