package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

/**
 * Esta clase representa el caso de uso de registro de un usuario.
 */
@RequiredArgsConstructor
@Component
public class UsuarioRegistroInteractor {
    /**
     * respositorio de usuarios.
     */
    private final UsuarioRepositoryPort usuarioRepository;

    /**
     * Constructor de la clase UsuarioRegistroInteractor.
     *
     * @param usuario
     * @return Usuario resgistrado
     */
    public Usuario save(final Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
