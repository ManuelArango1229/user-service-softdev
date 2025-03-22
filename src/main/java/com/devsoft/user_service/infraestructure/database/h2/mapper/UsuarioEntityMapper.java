package com.devsoft.user_service.infraestructure.database.h2.mapper;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;

public class UsuarioEntityMapper {
    public static UsuarioEntity toUsuarioEntity(Usuario usuario) {
        return new UsuarioEntity(usuario.getDni(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(),
                usuario.getRol());
    }

    public static Usuario toUsuario(UsuarioEntity usuarioEntity) {
        return new Usuario(usuarioEntity.getDni(), usuarioEntity.getNombre(), usuarioEntity.getEmail(),
                usuarioEntity.getPassword(), usuarioEntity.getRol());
    }
}
