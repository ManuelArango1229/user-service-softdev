package com.devsoft.user_service.infraestructure.rest.mapper;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;

/**
 * UsuarioDtoMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y objetos de transferencia de datos (DTOs)
 * relacionados con usuarios.
 */
public class UsuarioDtoMapper {

    /**
     * Convierte una entidad Usuario a un UsuarioResponseDto.
     *
     * @param usuarioDto la entidad Usuario
     * @return el DTO que contiene la información del usuario
     */
    public static Usuario mapToEntity(final UsuarioRequestDto usuarioDto) {
        Role role;
        try {
            role = Role.valueOf(usuarioDto.getRole().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RolInvalidoErrorException("Rol no válido: " + usuarioDto.getRole() + ". Debe ser ADMINISTRADOR, CLIENTE o REPARTIDOR.");
        }
        return new Usuario(
                usuarioDto.getDni(),
                usuarioDto.getName(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword(),
                role.name()
        );
    }
}
