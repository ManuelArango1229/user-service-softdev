package com.devsoft.user_service.infraestructure.rest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioLoginRequestDtoTest {

    @Test
    void testConstructorAndGettersSetters() {
        UsuarioLoginRequestDto dto = new UsuarioLoginRequestDto();
        dto.setEmail("user@test.com");
        dto.setPassword("password123");

        assertEquals("user@test.com", dto.getEmail());
        assertEquals("password123", dto.getPassword());

        UsuarioLoginRequestDto dtoWithArgs = new UsuarioLoginRequestDto("test2@test.com", "pass456");
        assertEquals("test2@test.com", dtoWithArgs.getEmail());
    }
}
