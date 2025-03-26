package com.devsoft.user_service.domain.repositories;

import java.util.Optional;

import com.devsoft.user_service.domain.entities.especializaciones.Cliente;

/**
 * Representa un puerto para interactuar con el repositorio de los clientes.
 */
public interface ClienteRepositoryPort {
    /**
     * Método que permite guardar un cliente en el repositorio.
     *
     * @param usuario
     * @return usuario
     */
    Cliente save(Cliente usuario);

    /**
     * Metodo que permite obtener un cliente por su correo.
     *
     * @param email
     * @return usuario
     */
    Optional<Cliente> findByEmail(String email);

    /**
     * Método que permite buscar un cliente por su correo electrónico.
     *
     * @param dni
     * @return usuario
     */
    Optional<Cliente> findByDni(String dni);
}
