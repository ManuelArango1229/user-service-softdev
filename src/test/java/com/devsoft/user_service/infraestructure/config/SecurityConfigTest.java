package com.devsoft.user_service.infraestructure.config;

import com.devsoft.user_service.infraestructure.jwt.JwtFiltroAutenticacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private AuthenticationProvider authenticationProvider;
    private JwtFiltroAutenticacion jwtFiltroAutenticacion;

    @BeforeEach
    void setUp() {
        authenticationProvider = mock(AuthenticationProvider.class);
        jwtFiltroAutenticacion = mock(JwtFiltroAutenticacion.class);
        securityConfig = new SecurityConfig(authenticationProvider, jwtFiltroAutenticacion);
    }

    @Test
    @DisplayName("Debe instanciar correctamente SecurityConfig")
    void testSecurityConfigInstantiation() {
        assertNotNull(securityConfig, "SecurityConfig no debe ser nulo");
    }

    @Test
    @DisplayName("Debe devolver una SecurityFilterChain válida")
    void testSecurityFilterChainCreation() throws Exception {
        // Arrange: creamos un mock de HttpSecurity
        HttpSecurity httpSecurity = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);

        // Simulamos el comportamiento encadenado de HttpSecurity
        when(httpSecurity.authorizeHttpRequests(any())).thenReturn(httpSecurity);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.headers(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);
        when(httpSecurity.build()).thenReturn(mock(org.springframework.security.web.DefaultSecurityFilterChain.class));

        // Act
        SecurityFilterChain filterChain = securityConfig.securityFilterChain(httpSecurity);

        // Assert
        assertNotNull(filterChain, "El SecurityFilterChain no debe ser nulo");
        verify(httpSecurity).authenticationProvider(authenticationProvider);
        verify(httpSecurity).addFilterBefore(jwtFiltroAutenticacion, 
            org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
    }
}
