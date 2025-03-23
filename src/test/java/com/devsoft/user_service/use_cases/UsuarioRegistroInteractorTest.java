package com.devsoft.user_service.use_cases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UsuarioRegistroInteractorTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @InjectMocks
    private UsuarioRegistroInteractor usuarioRegistroInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsuario() {
        // Arrange (Preparación)
        Usuario usuario = new Usuario("123", "Juan", "juan@example.com", "jdakjsd", "admin");
        when(usuarioRepository.save(usuario)).thenReturn(usuario); // Simulamos el comportamiento

        // Act (Ejecución)
        Usuario resultado = usuarioRegistroInteractor.save(usuario);

        // Assert (Verificación)
        assertNotNull(resultado);
        assertEquals("123", resultado.getDni());
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@example.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).save(usuario); // Verifica que se llamó exactamente una vez
    }
}
