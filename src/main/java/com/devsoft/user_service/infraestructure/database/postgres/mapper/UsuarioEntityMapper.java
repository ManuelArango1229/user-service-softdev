package com.devsoft.user_service.infraestructure.database.postgres.mapper;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.database.postgres.entity.UsuarioEntity;

/**
 * UsuarioEntityMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y entidades de base de datos relacionadas con
 * usuarios.
 */
public class UsuarioEntityMapper {
    /**
     * Convierte una entidad de dominio Usuario a una entidad de base de datos
     * UsuarioEntity.
     *
     * @param usuario la entidad de dominio que contiene la
     *                información del usuario
     * @return la entidad de base de datos UsuarioEntity
     */
    public static UsuarioEntity toUsuarioEntity(final Usuario usuario) {
        return new UsuarioEntity(usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getPassword().getValue(),
                usuario.getRole());
    }

    /**
     * Convierte una entidad de base de datos UsuarioEntity a una entidad del
     * dominio Usuario.
     *
     * @param usuarioEntity usuario la entidad de dominio que contiene la
     *                      información del usuario
     * @return la entidad de base de datos UsuarioEntity
     */
    public static Usuario toUsuario(final UsuarioEntity usuarioEntity) {
        return new Usuario(usuarioEntity.getDni(),
                usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword(),
                usuarioEntity.getRol().name());
    }
}
