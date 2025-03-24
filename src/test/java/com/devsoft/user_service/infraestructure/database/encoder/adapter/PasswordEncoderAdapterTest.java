package com.devsoft.user_service.infraestructure.database.encoder.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderAdapterTest {

    private PasswordEncoderAdapter encoderAdapter;

    @BeforeEach
    void setUp() {
        encoderAdapter = new PasswordEncoderAdapter();
    }

    @Test
    @DisplayName("Debe encriptar correctamente una contraseña")
    void testEncodePassword() {
        String rawPassword = "MiClave123!";
        String encodedPassword = encoderAdapter.encode(rawPassword);

        assertAll(
                () -> assertNotNull(encodedPassword, "La contraseña encriptada no debe ser nula"),
                () -> assertNotEquals(rawPassword, encodedPassword, "La contraseña encriptada no debe ser igual a la original"),
                () -> assertTrue(encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$"),
                        "La contraseña debe estar en formato bcrypt")
        );
    }

    @Test
    @DisplayName("Debe validar correctamente que la contraseña coincide")
    void testMatchesPasswordCorrect() {
        String rawPassword = "MiClave123!";
        String encodedPassword = encoderAdapter.encode(rawPassword);

        assertTrue(encoderAdapter.matches(rawPassword, encodedPassword), "La contraseña debería coincidir");
    }

    @Test
    @DisplayName("Debe fallar cuando la contraseña no coincide")
    void testMatchesPasswordIncorrect() {
        String rawPassword = "MiClave123!";
        String wrongPassword = "OtraClave456!";
        String encodedPassword = encoderAdapter.encode(rawPassword);

        assertFalse(encoderAdapter.matches(wrongPassword, encodedPassword), "La contraseña no debería coincidir");
    }
}
