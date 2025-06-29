package com.devsoft.user_service.infraestructure.database.postgres.mapper;

import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.ClienteEntity;

/**
 * ClienteEntityMapper es una clase de utilidad que proporciona métodos para
 * convertir
 * entre entidades de dominio y entidades de base de datos relacionadas con
 * clientes.
 */
public class ClienteEntityMapper {
    /**
     * Convierte una entidad de dominio Cliente a una entidad de base de datos
     * ClienteEntity.
     *
     * @param usuario la entidad de dominio que contiene la
     *                información del usuario
     * @return la entidad de base de datos ClienteEntity
     */
    public static ClienteEntity toClienteEntity(final Cliente usuario) {
        return new ClienteEntity(usuario.getDni(),
                usuario.getNombre(),
                usuario.getEmail().getValue(),
                usuario.getPassword().getValue(),
                usuario.getEdad(),
                usuario.getDireccion(),
                usuario.getGenero(), usuario.getTelefono());
    }

    /**
     * Convierte una entidad de cliente de base de datos ClienteEntity a una entidad
     * del dominio Cliente.
     *
     * @param usuarioEntity usuario la entidad de dominio que contiene la
     *                      información del usuario
     * @return la entidad de base de datos ClienteEntity
     */
    public static Cliente toCliente(final ClienteEntity usuarioEntity) {
        return new Cliente(usuarioEntity.getDni(),
                usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword(),
                usuarioEntity.getEdad(),
                usuarioEntity.getDireccion(),
                usuarioEntity.getGenero(), usuarioEntity.getTelefono());
    }
}
