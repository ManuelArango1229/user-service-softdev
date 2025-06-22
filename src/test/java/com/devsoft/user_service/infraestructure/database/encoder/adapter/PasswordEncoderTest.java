package com.devsoft.user_service.infraestructure.database.encoder.adapter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordEncoderAdapterTest {

    private PasswordEncoderAdapter passwordEncoderAdapter;

    @BeforeEach
    void setUp() {
        passwordEncoderAdapter = new PasswordEncoderAdapter();
    }

    @Test
    void testEncode() {

        String rawPassword = "MiContraseñaSegura123";

        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void testMatches() {
        String rawPassword = "MiContraseñaSegura123";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        boolean matches = passwordEncoderAdapter.matches(rawPassword, encodedPassword);

        assertTrue(matches);
    }

    @Test
    void testGetEncoder() {

        BCryptPasswordEncoder encoder = passwordEncoderAdapter.getEncoder();

        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }
}