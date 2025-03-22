package com.devsoft.user_service.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase Usuario representa a un usuario en el sistema.
 * Contiene información básica como DNI, nombre, email, contraseña y rol.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    /**
     * Identificador único del usuario.
     */
    private String dni;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Correo electrónico del usuario.
     */
    private String email;
    /**
     * contraseña del usuario.
     */
    private String password;
    /**
     * Rol del usuario.
     */
    private String rol;
}
