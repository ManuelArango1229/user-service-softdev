package com.devsoft.user_service.infraestructure.rest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioRequestDtoTest {

    @Test
    void testConstructorAndGettersSetters() {
        UsuarioRequestDto dto = new UsuarioRequestDto(
            "12345678", "Carlos", "carlos@test.com", "Password1", "CLIENTE"
        );

        assertEquals("12345678", dto.getDni());
        assertEquals("Carlos", dto.getName());
        assertEquals("carlos@test.com", dto.getEmail());
        assertEquals("Password1", dto.getPassword());
        assertEquals("CLIENTE", dto.getRole());
    }
}
