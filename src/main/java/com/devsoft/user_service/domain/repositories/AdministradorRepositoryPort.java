package com.devsoft.user_service.domain.repositories;

import java.util.Optional;

import com.devsoft.user_service.domain.entities.especializaciones.Administrador;

/**
 * Representa un puerto para interactuar con el repositorio de administradores.
 */
public interface AdministradorRepositoryPort {
    /**
     * Método que permite guardar un administrador en el repositorio.
     *
     * @param usuario
     * @return usuario
     */
    Administrador save(Administrador usuario);

    /**
     * Metodo que permite obtener un administrador por su correo.
     *
     * @param email
     * @return usuario
     */
    Optional<Administrador> findByEmail(String email);

    /**
     * Método que permite buscar un administrador por su correo electrónico.
     *
     * @param dni
     * @return usuario
     */
    Optional<Administrador> findByDni(String dni);
}
