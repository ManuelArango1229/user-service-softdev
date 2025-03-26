package com.devsoft.user_service.infraestructure.database.h2.mapper;

import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.RepartidorEntity;

/**
 * RepartidorEntityMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y entidades de base de datos relacionadas con
 * repartidores.
 */
public class RepartidorEntityMapper {
    /**
     * Convierte una entidad de dominio Repartidor a una entidad de base de datos
     * RepartidorEntity.
     *
     * @param usuario la entidad de dominio que contiene la
     *                información del usuario
     * @return la entidad de base de datos RepartidorEntity
     */
    public static RepartidorEntity toRepartidorEntity(final Repartidor usuario) {
        return new RepartidorEntity(usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getPassword().getValue(),
                usuario.getMetodoAsignado());
    }

    /**
     * Convierte una entidad de base de datos RepartidorEntity a una entidad del
     * dominio Repartidor.
     *
     * @param usuarioEntity usuario la entidad de dominio que contiene la
     *                      información del usuario
     * @return la entidad de base de datos RepartidorEntity
     */
    public static Repartidor toRepartidor(final RepartidorEntity usuarioEntity) {
        return new Repartidor(usuarioEntity.getDni(),
                usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword(),
                usuarioEntity.getMetodoAsignado());
    }
}
