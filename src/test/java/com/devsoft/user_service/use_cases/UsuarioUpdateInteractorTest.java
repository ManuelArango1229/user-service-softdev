package com.devsoft.user_service.use_cases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;

class UsuarioUpdateInteractorTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @InjectMocks
    private UsuarioUpdateInteractor usuarioUpdateInteractor;

    private Usuario usuarioExistente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuarioExistente = new Usuario("12345678", "Juan Pérez", "juan.perez@example.com", "Password123",
                "ADMINISTRADOR");
    }

    @Test
    void testActualizarUsuarioExistente() {
        UsuarioUpdateDto updatedData = new UsuarioUpdateDto("Nuevo Nombre", "nuevo.email@example.com", "nuevaPassword");

        when(usuarioRepository.findByDni("12345678")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.encode("nuevaPassword")).thenReturn("hashedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario updatedUser = usuarioUpdateInteractor.execute("12345678", updatedData);

        assertEquals("Nuevo Nombre", updatedUser.getNombre());
        assertEquals("nuevo.email@example.com", updatedUser.getEmail().getValue());
        assertEquals("hashedPassword", updatedUser.getPassword().getValue());

        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testActualizarUsuarioInexistente() {
        when(usuarioRepository.findByDni("99999999")).thenReturn(Optional.empty());

        UsuarioUpdateDto updatedData = new UsuarioUpdateDto("Nombre", "email@example.com", "password");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioUpdateInteractor.execute("99999999", updatedData);
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void testActualizarSoloNombre() {
        UsuarioUpdateDto updatedData = new UsuarioUpdateDto("Solo Nombre", null, null);

        when(usuarioRepository.findByDni("12345678")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario updatedUser = usuarioUpdateInteractor.execute("12345678", updatedData);

        assertEquals("Solo Nombre", updatedUser.getNombre());
        assertEquals("juan.perez@example.com", updatedUser.getEmail().getValue());
        assertEquals("Password123", updatedUser.getPassword().getValue());
    }
}
