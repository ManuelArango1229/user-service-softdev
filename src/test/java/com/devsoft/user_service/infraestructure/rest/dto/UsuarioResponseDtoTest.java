package com.devsoft.user_service.infraestructure.rest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioResponseDtoTest {

    @Test
    void testConstructorAndGettersSetters() {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setDni("00000000");
        dto.setName("Laura");
        dto.setEmail("laura@test.com");
        dto.setRole("ADMIN");
        dto.setToken("jwt-token");

        assertEquals("00000000", dto.getDni());
        assertEquals("Laura", dto.getName());
        assertEquals("laura@test.com", dto.getEmail());
        assertEquals("ADMIN", dto.getRole());
        assertEquals("jwt-token", dto.getToken());

        UsuarioResponseDto dtoWithArgs = new UsuarioResponseDto("123", "Jose", "jose@test.com", "USER", "tk-123");
        assertEquals("Jose", dtoWithArgs.getName());
    }
}
