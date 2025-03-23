package com.devsoft.user_service.domain.services;

import com.devsoft.user_service.domain.entities.Usuario;

/**
 * Interfaz para obtener el nombre de usuario de un objeto {@code Usuario}.
 */
public interface UserDetailsPort {
    /**
     * Obtiene el nombre de usuario de un objeto {@code Usuario}.
     *
     * @param usuario El objeto {@code Usuario} del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario como una cadena de texto ({@code String}).
     * Si el usuario es {@code null}, puede lanzar una excepción o devolver un valor por defecto según la implementación.
     */
    String getUsername(Usuario usuario);
}
