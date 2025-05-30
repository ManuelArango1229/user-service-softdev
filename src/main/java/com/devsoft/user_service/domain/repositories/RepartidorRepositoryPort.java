package com.devsoft.user_service.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;

/**
 * Representa un puerto para interactuar con el repositorio de repartidores.
 */
public interface RepartidorRepositoryPort {
    /**
     * Método que permite guardar un repartidor en el repositorio.
     *
     * @param usuario
     * @return usuario
     */
    Repartidor save(Repartidor usuario);

    /**
     * Metodo que permite obtener un repartidor por su correo.
     *
     * @param email
     * @return usuario
     */
    Optional<Repartidor> findByEmail(String email);

    /**
     * Método que permite buscar un repartidor por su correo electrónico.
     *
     * @param dni
     * @return usuario
     */
    Optional<Repartidor> findByDni(String dni);
    /**
     * Metodo para buscar todos los repartidores.
     *
     * @return Lista de repartidores.
     */
    List<Repartidor> findAll();
}
