package com.devsoft.user_service.domain.entities;

import lombok.Data;

/**
 * La clase Usuario representa a un usuario en el sistema.
 * Contiene información básica como DNI, nombre, email, contraseña y rol.
 */
@Data
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
    private String role;

    /**
     * @param dniParam
     * @param nombreParam
     * @param emailParam
     * @param passwordParam
     * @param roleParam
     */
    public Usuario(final String dniParam, final String nombreParam,
            final String emailParam,
            final String passwordParam,
            final String roleParam) {
        this.dni = dniParam;
        this.nombre = nombreParam;
        this.email = emailParam;
        this.password = passwordParam;
        this.role = roleParam;
    }

}
