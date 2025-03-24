package com.devsoft.user_service.infraestructure.rest.mapper;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.value_objects.Email;
import com.devsoft.user_service.domain.value_objects.Password;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDtoMapperTest {

    @Test
    @DisplayName("Debe convertir UsuarioRequestDto a Usuario")
    void testMapToEntity() {
        UsuarioRequestDto dto = new UsuarioRequestDto(
                "12345678",
                "Juan Pérez",
                "juan@example.com",
                "Clave123!",
                "CLIENTE"
        );

        Usuario usuario = UsuarioDtoMapper.mapToEntity(dto);

        assertAll(
                () -> assertEquals(dto.getDni(), usuario.getDni()),
                () -> assertEquals(dto.getName(), usuario.getNombre()),
                () -> assertEquals(dto.getEmail(), usuario.getEmail().getValue()),
                () -> assertEquals(dto.getPassword(), usuario.getPassword().getValue()),
                () -> assertEquals(dto.getRole(), usuario.getRole().name())
        );
    }

    @Test
    @DisplayName("Debe convertir Usuario a UsuarioResponseDto")
    void testMapToDto() {
        Usuario usuario = new Usuario(
                "87654321",
                "Laura Gómez",
                "laura@example.com",
                "Segura456!",
                "REPARTIDOR"
        );

        UsuarioResponseDto dto = UsuarioDtoMapper.mapToDto(usuario);

        assertAll(
                () -> assertEquals(usuario.getDni(), dto.getDni()),
                () -> assertEquals(usuario.getNombre(), dto.getName()),
                () -> assertEquals(usuario.getEmail().getValue(), dto.getEmail()),
                () -> assertEquals(usuario.getRole().name(), dto.getRole())
        );
    }
}
