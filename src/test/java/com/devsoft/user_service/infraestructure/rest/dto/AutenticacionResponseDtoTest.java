package com.devsoft.user_service.infraestructure.rest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutenticacionResponseDtoTest {

    @Test
    void testConstructorAndGettersSetters() {
        AutenticacionResponseDto dto = new AutenticacionResponseDto();
        dto.setToken("test-token");

        assertEquals("test-token", dto.getToken());

        AutenticacionResponseDto dtoWithArgs = new AutenticacionResponseDto("another-token");
        assertEquals("another-token", dtoWithArgs.getToken());
    }
}
