package com.devsoft.user_service.infraestructure.jwt.adapter;


import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServicioAdapterTest {

    @InjectMocks
    private JwtServicioAdapter jwtServicioAdapter;

    private static final String SECRET_KEY = "mysupersecretkeymysupersecretkeymysupersecretkey";
    private static final String USERNAME = "testuser";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtServicioAdapter, "secret", SECRET_KEY);
    }

    @Test
    @DisplayName("Debe generar un token válido para un usuario")
    void testObtenerToken() {
        String token = jwtServicioAdapter.obtenerToken(USERNAME);
        assertNotNull(token, "El token no debería ser nulo");
    }

    @Test
    @DisplayName("Debe validar correctamente un token generado")
    void testValidarToken() {
        String token = jwtServicioAdapter.obtenerToken(USERNAME);
        assertTrue(jwtServicioAdapter.validarToken(token, USERNAME), "El token debería ser válido");
    }

    @Test
    @DisplayName("Debe extraer el nombre de usuario del token correctamente")
    void testObtenerUsernameDesdeToken() {
        String token = jwtServicioAdapter.obtenerToken(USERNAME);
        String extractedUsername = jwtServicioAdapter.obtenerUsernameDesdeToken(token);
        assertEquals(USERNAME, extractedUsername, "El nombre de usuario extraído debe coincidir");
    }

    @Test
    @DisplayName("Debe validar si un token no ha expirado")
    void testValidarTokenVencido() throws InterruptedException {
        String token = Jwts.builder()
                .subject(USERNAME)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10000)) // 10 segundos de validez
                .signWith(jwtServicioAdapter.obtenerLlave())
                .compact();

        Thread.sleep(2000);
        assertFalse(jwtServicioAdapter.validarTokenVencido(token), "El token deberia ser valido");
    }

    @Test
    @DisplayName("Debe extraer los claims correctamente")
    void testObtenerTodosLosClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ADMIN");
        String token = jwtServicioAdapter.obtenerToken(claims, USERNAME);

        Map<String, Object> extractedClaims = jwtServicioAdapter.obtenerTodosLosClaims(token);
        assertEquals("ADMIN", extractedClaims.get("role"), "El claim 'role' debería ser 'ADMIN'");
    }
}
