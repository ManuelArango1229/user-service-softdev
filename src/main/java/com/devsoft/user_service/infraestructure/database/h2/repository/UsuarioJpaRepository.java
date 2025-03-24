package com.devsoft.user_service.infraestructure.database.h2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;

/**
 * UsuarioJpaRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos CRUD para la entidad UsuarioEntity.
 * <p>
 * Esta interfaz permite realizar operaciones de base de datos sobre la entidad
 * UsuarioEntity
 * utilizando UUID como identificador.
 * </p>
 */
public interface UsuarioJpaRepository
        extends JpaRepository<UsuarioEntity, Long> {

        /**
         * Busca un usuario en la base de datos por su dirección de correo electrónico.
         *
         * @param email la dirección de correo electrónico del usuario a buscar.
         * @return un {@code Optional<UsuarioEntity>} que contiene la entidad del usuario
         *         si se encuentra en la base de datos, o un {@code Optional.empty()} si no existe.
        */
        Optional<UsuarioEntity> findByEmail(String email);

        /**
         * Método que permite buscar un usuario por su DNI.
         *
         * @param dni el DNI del usuario a buscar
         * @return la entidad de dominio UsuarioEntity
         */
        UsuarioEntity findByDni(String dni);
}
