package com.devsoft.user_service.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UsuarioLoginRequestDto es un objeto de transferencia de datos (DTO) que
 * representa la información
 * necesaria para autenticar un usuario en el sistema.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginRequestDto {

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * La contraseña del usuario.
     */
    private String password;

}
