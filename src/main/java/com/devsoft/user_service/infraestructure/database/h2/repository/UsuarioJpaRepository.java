package com.devsoft.user_service.infraestructure.database.h2.repository;

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
         * Método que permite buscar un usuario por su DNI.
         *
         * @param dni el DNI del usuario a buscar
         * @return la entidad de dominio UsuarioEntity
         */
        UsuarioEntity findByDni(String dni);
}
