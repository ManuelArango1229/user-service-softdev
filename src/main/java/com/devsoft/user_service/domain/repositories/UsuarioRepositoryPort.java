package com.devsoft.user_service.domain.repositories;

import com.devsoft.user_service.domain.entities.Usuario;

/**
 * La interfaz UsuarioRepositoryPort define los métodos necesarios para
 * interactuar con el repositorio de usuarios.
 */
public interface UsuarioRepositoryPort {
    /**
     * Método que permite guardar un usuario en el repositorio.
     *
     * @param usuario
     * @return usuario
     */
    Usuario save(Usuario usuario);
}
