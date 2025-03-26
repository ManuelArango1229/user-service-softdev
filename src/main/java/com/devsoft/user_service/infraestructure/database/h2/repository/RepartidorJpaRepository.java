package com.devsoft.user_service.infraestructure.database.h2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.RepartidorEntity;

/**
 * RepartidorJpaRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos CRUD para la entidad RepartidorEntity.
 * <p>
 * Esta interfaz permite realizar operaciones de base de datos sobre la entidad
 * RepartidorEntity
 * </p>
 */
public interface RepartidorJpaRepository
        extends JpaRepository<RepartidorEntity, String> {

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar.
     * @return un {@code Optional<RepartidorEntity>} que contiene la entidad del
     *         usuario
     *         si se encuentra en la base de datos, o un {@code Optional.empty()} si
     *         no existe.
     */
    Optional<RepartidorEntity> findByEmail(String email);

    /**
     * Método que permite buscar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a buscar
     * @return la entidad de dominio RepartidorEntity
     */
    RepartidorEntity findByDni(String dni);
}
