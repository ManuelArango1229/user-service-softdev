
package com.devsoft.user_service.infraestructure.config;

import com.devsoft.user_service.infraestructure.jwt.JwtFiltroAutenticacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationProvider;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        AuthenticationProvider authenticationProvider = Mockito.mock(AuthenticationProvider.class);
        JwtFiltroAutenticacion jwtFiltroAutenticacion = Mockito.mock(JwtFiltroAutenticacion.class);

        securityConfig = new SecurityConfig(authenticationProvider, jwtFiltroAutenticacion);
    }

    @Test
    @DisplayName("Debe instanciar correctamente la clase SecurityConfig")
    void testSecurityConfigInstantiation() {
        assertNotNull(securityConfig, "La instancia de SecurityConfig no debe ser nula");
    }
}
