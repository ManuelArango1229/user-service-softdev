package com.devsoft.user_service.infraestructure.database.h2.adapter;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.h2.mapper.UsuarioEntityMapper;
import com.devsoft.user_service.infraestructure.database.h2.repository.UsuarioJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntityMapper.toUsuarioEntity(usuario);
        Usuario saveUser = UsuarioEntityMapper.toUsuario(usuarioJpaRepository.save(usuarioEntity));
        return saveUser;
    }
}
