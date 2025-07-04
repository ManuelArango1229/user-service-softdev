package com.devsoft.user_service.domain.repositories;

import java.util.Optional;

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

    /**
     * Metodo que permite obtener un usuario por su correo.
     *
     * @param email
     * @return usuario
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Método que permite buscar un usuario por su correo electrónico.
     *
     * @param dni
     * @return usuario
     */
    Optional<Usuario> findByDni(String dni);
    /**
     * Metodo que elimina un usuario por su DNI.
     *
     * @param dni
     */
    void deleteByDni(String dni);
}
