package com.devsoft.user_service.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UsuarioResponseDto es un objeto de transferencia de datos (DTO) que
 * representa la información
 * de un usuario que se devuelve en las respuestas de la API.
 */
@AllArgsConstructor
@Data
public class UsuarioResponseDto {
    /**
     * El Documento Nacional de Identidad (DNI) del usuario.
     */
    private String dni;
    /**
     * El nombre del usuario.
     */
    private String name;
    /**
     * El correo electrónico del usuario.
     */
    private String email;
    /**
     * El rol del usuario en el sistema.
     */
    private String role;

    /**
     * El token JWT generado para el usuario.
     */
    private String token;
}
