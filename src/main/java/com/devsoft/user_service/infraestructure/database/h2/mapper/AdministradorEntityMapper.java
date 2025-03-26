package com.devsoft.user_service.infraestructure.database.h2.mapper;

import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.AdministradorEntity;

/**
 * UsuarioEntityMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y entidades de base de datos relacionadas con
 * usuarios.
 */
public class AdministradorEntityMapper {
    /**
     * Convierte una entidad de dominio Administrador a una entidad de base de datos
     * AdministradorEntity.
     *
     * @param usuario la entidad de dominio que contiene la
     *                información del usuario
     * @return la entidad de base de datos AdministradorEntity
     */
    public static AdministradorEntity toAdministradorEntity(final Administrador usuario) {
        return new AdministradorEntity(usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getPassword().getValue());
    }

    /**
     * Convierte una entidad de la base de datos AdministradorEntity a una entidad
     * del dominio Administrador.
     *
     * @param usuarioEntity usuario la entidad de dominio que contiene la
     *                      información del usuario
     * @return La entidad de dominio Administrador
     */
    public static Administrador toAdministrador(final AdministradorEntity usuarioEntity) {
        return new Administrador(usuarioEntity.getDni(),
                usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword());
    }
}
