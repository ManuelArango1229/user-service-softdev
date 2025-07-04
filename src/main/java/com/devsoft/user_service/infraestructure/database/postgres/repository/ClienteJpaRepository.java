package com.devsoft.user_service.infraestructure.database.postgres.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.ClienteEntity;

/**
 * ClienteJpaRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos CRUD para la entidad ClienteEntity.
 * <p>
 * Esta interfaz permite realizar operaciones de base de datos sobre la entidad
 * ClienteEntity
 * </p>
 */
@Profile("postgres")
public interface ClienteJpaRepository
        extends JpaRepository<ClienteEntity, String> {

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar.
     * @return un {@code Optional<ClienteEntity>} que contiene la entidad del
     *         usuario
     *         si se encuentra en la base de datos, o un {@code Optional.empty()} si
     *         no existe.
     */
    Optional<ClienteEntity> findByEmail(String email);

    /**
     * Método que permite buscar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a buscar
     * @return la entidad de dominio ClienteEntity
     */
    ClienteEntity findByDni(String dni);
}
