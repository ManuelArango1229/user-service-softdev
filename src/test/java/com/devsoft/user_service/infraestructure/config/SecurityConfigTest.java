package com.devsoft.user_service.infraestructure.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        securityConfig = new SecurityConfig();
    }

    @Test
    @DisplayName("Debe instanciar correctamente la clase SecurityConfig")
    void testSecurityConfigInstantiation() {
        assertNotNull(securityConfig, "La instancia de SecurityConfig no debe ser nula");
    }
}
