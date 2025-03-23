package com.devsoft.user_service.infraestructure.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.services.UserDetailsPort;

/**
 * Clase que implementa la interfaz UserDetailsPort.
 */
@Component
public class UserDetailsAdapter implements UserDetailsPort {

    /**
     * Obtiene el nombre de usuario de un objeto {@code Usuario}.
     *
     * @param usuario El objeto {@code Usuario} del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario como una cadena de texto ({@code String}).
     *         Si el usuario es {@code null}, puede lanzar una excepción o devolver un valor por defecto según la implementación.
     */
    @Override
    public String getUsername(final Usuario usuario) {
        UserDetails userDetails = User.withUsername(usuario.getEmail().getValue())
            .password(usuario.getPassword().getValue())
            .roles(usuario.getRole().name())
            .build();
        return userDetails.getUsername();
    }
}
