package com.devsoft.user_service.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UsuarioRequestDto es un objeto de transferencia de datos (DTO) que representa
 * la información
 * necesaria para registrar un usuario en el sistema.
 */
@AllArgsConstructor
@Data
public class UsuarioRequestDto {
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
     * La contraseña del usuario.
     */
    private String password;
    /**
     * El rol del usuario en el sistema.
     */
    private String role;
}
