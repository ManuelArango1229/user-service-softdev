package com.devsoft.user_service.use_cases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UsuarioRegistroInteractorTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;
    @Mock
    private PasswordEncoderPort passwordEncoder;
    @InjectMocks
    private UsuarioRegistroInteractor usuarioRegistroInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsuario() {
        Usuario usuario = new Usuario("123", "Juan", "juan@example.com", "Hola123HA", "ADMINISTRADOR");
        String encodedPassword = "encoded_Hola123HA";
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(passwordEncoder.encode("Hola123HA")).thenReturn(encodedPassword);

        Usuario resultado = usuarioRegistroInteractor.save(usuario);

        assertNotNull(resultado);
        assertEquals("123", resultado.getDni());
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@example.com", resultado.getEmail().getValue());
        verify(usuarioRepository, times(1)).save(usuario);

        verify(passwordEncoder, times(1)).encode("Hola123HA");

        verify(usuarioRepository).save(argThat(user -> user.getPassword().getValue().equals(encodedPassword)));
    }
}
