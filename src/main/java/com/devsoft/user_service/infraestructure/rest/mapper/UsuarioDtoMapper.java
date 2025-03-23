package com.devsoft.user_service.infraestructure.rest.mapper;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioResponseDto;

/**
 * UsuarioDtoMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y objetos de transferencia de datos (DTOs)
 * relacionados con usuarios.
 */
public class UsuarioDtoMapper {
    /**
     * Convierte un UsuarioRequestDto a una entidad Usuario.
     *
     * @param usuario el DTO que contiene la información del usuario
     * @return la entidad Usuario
     */
    public static UsuarioResponseDto mapToDto(final Usuario usuario) {
        return new UsuarioResponseDto(usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getRole().name());
    }

    /**
     * Convierte una entidad Usuario a un UsuarioResponseDto.
     *
     * @param usuarioDto la entidad Usuario
     * @return el DTO que contiene la información del usuario
     */
    public static Usuario mapToEntity(final UsuarioRequestDto usuarioDto) {
        return new Usuario(usuarioDto.getDni(),
                usuarioDto.getName(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword(),
                usuarioDto.getRole());
    }
}
