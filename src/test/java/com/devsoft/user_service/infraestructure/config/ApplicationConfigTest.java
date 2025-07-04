package com.devsoft.user_service.infraestructure.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.encoder.adapter.PasswordEncoderAdapter;
import com.devsoft.user_service.infraestructure.database.postgres.adapter.UsuarioRepositoryAdapter;
import com.devsoft.user_service.domain.entities.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

class ApplicationConfigTest {

    @Mock
    private UsuarioRepositoryAdapter userRepository;

    @Mock
    private PasswordEncoderAdapter passwordEncoderAdapter;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe instanciar correctamente ApplicationConfig")
    void testApplicationConfigInstantiation() {
        assertNotNull(applicationConfig, "La instancia de ApplicationConfig no debe ser nula");
    }

    @Test
    @DisplayName("Debe crear un AuthenticationManager correctamente")
    void testAuthenticationManager() throws Exception {
        AuthenticationManager mockManager = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager authenticationManager = applicationConfig.authenticationManager(authenticationConfiguration);

        assertNotNull(authenticationManager, "El AuthenticationManager no debe ser nulo");
        assertEquals(mockManager, authenticationManager, "Debe devolver la misma instancia simulada");
    }

    @Test
    @DisplayName("Debe crear un AuthenticationProvider correctamente")
    void testAuthenticationProvider() {
        when(passwordEncoderAdapter.getEncoder()).thenReturn(mock(BCryptPasswordEncoder.class));

        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

        assertNotNull(authenticationProvider, "El AuthenticationProvider no debe ser nulo");
        assertTrue(authenticationProvider instanceof DaoAuthenticationProvider, "Debe ser una instancia de DaoAuthenticationProvider");
    }

    @Test
    @DisplayName("Debe lanzar UsernameNotFoundException si el usuario no existe")
    void testUserDetailServiceUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserDetailsService userDetailsService = applicationConfig.userDetailService();

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test@example.com"));
    }

    @Test
    @DisplayName("Debe cargar los detalles del usuario correctamente si el usuario existe")
    void testUserDetailServiceUserFound() {
    Usuario mockUser = new Usuario(
        "12345678",
        "John Doe",
        "john@example.com",
        "Password123",
        "CLIENTE"
    );

    when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(mockUser));


        UserDetailsService userDetailsService = applicationConfig.userDetailService();
        UserDetails userDetails = userDetailsService.loadUserByUsername("john@example.com");

        assertNotNull(userDetails, "Los detalles del usuario no deben ser nulos");
        assertEquals("Password123", userDetails.getPassword(), "La contraseña debe coincidir");
        assertEquals("john@example.com", userDetails.getUsername(), "El correo debe coincidir");
        assertTrue(
        userDetails.getAuthorities()
               .stream()
               .anyMatch(auth -> auth.getAuthority().equals("CLIENTE")),
        "Se esperaba la autoridad CLIENTE"
        );    
    }
}
