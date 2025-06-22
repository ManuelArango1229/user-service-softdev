package com.devsoft.user_service.infraestructure.rest.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;

public class UsuarioDtoMapperTest {

    @Test
    public void mapToEntity_shouldConvertDtoToUsuario_whenRoleIsValid() {
        // Arrange
        UsuarioRequestDto dto = new UsuarioRequestDto("123456789", "Juan", "juan@example.com", "Password123*", "cliente");

        dto.setDni("123456789");
        dto.setName("Juan");
        dto.setEmail("juan@example.com");
        dto.setPassword("Password123*");
        dto.setRole("cliente");

        // Act
        Usuario usuario = UsuarioDtoMapper.mapToEntity(dto);

        // Assert
        assertEquals("123456789", usuario.getDni());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("juan@example.com", usuario.getEmail().getValue());
        assertEquals("Password123*", usuario.getPassword().getValue());
        assertEquals("CLIENTE", usuario.getRole().name());
    }

    @Test
    public void mapToEntity_shouldThrowRolInvalidoErrorException_whenRoleIsInvalid() {
        // Arrange
        UsuarioRequestDto dto = new UsuarioRequestDto("123456789", "Juan", "juan@example.com", "Password123*", "cliente");
        dto.setDni("987654321");
        dto.setName("Ana");
        dto.setEmail("ana@example.com");
        dto.setPassword("Password456*");
        dto.setRole("invalidRole");

        // Act & Assert
        RolInvalidoErrorException exception = assertThrows(
                RolInvalidoErrorException.class,
                () -> UsuarioDtoMapper.mapToEntity(dto)
        );

        assertTrue(exception.getMessage().contains("Rol no válido"));
        assertTrue(exception.getMessage().contains("invalidRole"));
    }
}
