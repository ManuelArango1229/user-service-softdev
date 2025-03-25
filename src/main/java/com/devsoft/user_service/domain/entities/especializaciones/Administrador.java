package com.devsoft.user_service.domain.entities.especializaciones;

import com.devsoft.user_service.domain.entities.Usuario;

/**
 * La clase Administrador representa un tipo especializado de Usuario.
 * Extiende la clase Usuario y establece un rol específico para el usuario.
 */
public class Administrador extends Usuario {
    /**
     * Construye un nuevo Administrador con los detalles especificados.
     *
     * @param dniParam      el identificador único para el usuario
     * @param nombreParam   el nombre del usuario
     * @param emailParam    la dirección de correo electrónico del usuario
     * @param passwordParam la contraseña para la cuenta del usuario
     */
    public Administrador(final String dniParam, final String nombreParam, final String emailParam,
            final String passwordParam) {
        super(dniParam, nombreParam, emailParam, passwordParam, "REPARTIDOR");
    }
}
