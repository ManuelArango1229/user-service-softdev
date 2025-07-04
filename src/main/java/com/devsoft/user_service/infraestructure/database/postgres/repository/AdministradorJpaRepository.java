package com.devsoft.user_service.infraestructure.database.postgres.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.AdministradorEntity;

/**
 * AdministradorJpaRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos CRUD para la entidad AdministradorEntity.
 * <p>
 * Esta interfaz permite realizar operaciones de base de datos sobre la entidad
 * AdministradorEntity
 * </p>
 */
@Profile("postgres")
public interface AdministradorJpaRepository
        extends JpaRepository<AdministradorEntity, String> {

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar.
     * @return un {@code Optional<AdministradorEntity>} que contiene la entidad del
     *         usuario
     *         si se encuentra en la base de datos, o un {@code Optional.empty()} si
     *         no existe.
     */
    Optional<AdministradorEntity> findByEmail(String email);

    /**
     * Método que permite buscar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a buscar
     * @return la entidad de dominio AdministradorEntity
     */
    AdministradorEntity findByDni(String dni);
}
