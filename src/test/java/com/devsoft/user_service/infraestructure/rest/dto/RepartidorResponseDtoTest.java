package com.devsoft.user_service.infraestructure.rest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepartidorResponseDtoTest {

    @Test
    void testConstructorAndGettersSetters() {
        RepartidorResponseDto dto = new RepartidorResponseDto();
        dto.setDni("12345678");
        dto.setNombre("Juan");
        dto.setEmail("juan@test.com");
        dto.setRole("REPARTIDOR");
        dto.setMetodoAsignado("MOTO");

        assertEquals("12345678", dto.getDni());
        assertEquals("Juan", dto.getNombre());
        assertEquals("juan@test.com", dto.getEmail());
        assertEquals("REPARTIDOR", dto.getRole());
        assertEquals("MOTO", dto.getMetodoAsignado());

        RepartidorResponseDto dtoWithArgs = new RepartidorResponseDto("123", "Ana", "ana@test.com", "ROLE", "BICI");
        assertEquals("Ana", dtoWithArgs.getNombre());
    }
}
