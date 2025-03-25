package com.devsoft.user_service.infraestructure.jwt;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.devsoft.user_service.infraestructure.jwt.adapter.JwtServicioAdapter;

import java.io.IOException;

class JwtFiltroAutenticacionTest {

    @Mock
    private JwtServicioAdapter jwtServicio;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtFiltroAutenticacion jwtFiltroAutenticacion;

    private static final String TOKEN_VALIDO = "valid-jwt-token";
    private static final String TOKEN_PREFIX = "Bearer ";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext(); // Limpiar autenticación antes de cada test
    }

    @Test
    @DisplayName("Debe permitir la solicitud si no hay token en la cabecera")
    void testSinTokenEnCabecera() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtFiltroAutenticacion.doFilterInternal(request, response, filterChain);

        verify(jwtServicio, never()).obtenerUsernameDesdeToken(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    @DisplayName("Debe permitir la solicitud si el token es inválido")
    void testTokenInvalido() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(TOKEN_PREFIX + TOKEN_VALIDO);
        when(jwtServicio.obtenerUsernameDesdeToken(TOKEN_VALIDO)).thenReturn(null);

        jwtFiltroAutenticacion.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }
}
