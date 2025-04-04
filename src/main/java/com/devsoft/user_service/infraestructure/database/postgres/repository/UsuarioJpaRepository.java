package com.devsoft.user_service.infraestructure.database.postgres.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.postgres.entity.UsuarioEntity;

/**
 * RepartidorJpaRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos CRUD para la entidad RepartidorEntity.
 * <p>
 * Esta interfaz permite realizar operaciones de base de datos sobre la entidad
 * RepartidorEntity
 * </p>
 */
@Profile("postgres")
public interface UsuarioJpaRepository
        extends JpaRepository<UsuarioEntity, String> {

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar.
     * @return un {@code Optional<RepartidorEntity>} que contiene la entidad del
     *         usuario
     *         si se encuentra en la base de datos, o un {@code Optional.empty()} si
     *         no existe.
     */
    Optional<UsuarioEntity> findByEmail(String email);

    /**
     * Método que permite buscar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a buscar
     * @return la entidad de dominio RepartidorEntity
     */
    UsuarioEntity findByDni(String dni);

    /**
     * Método que permite eliminar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a eliminar
     */
    void deleteByDni(String dni);
}
