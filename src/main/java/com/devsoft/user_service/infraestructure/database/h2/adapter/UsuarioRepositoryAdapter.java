package com.devsoft.user_service.infraestructure.database.h2.adapter;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.h2.mapper.UsuarioEntityMapper;
import com.devsoft.user_service.infraestructure.database.h2.repository.UsuarioJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Clase adaptadora que implementa la interfaz UsuarioRepositoryPort
 * Esta clase es responsable de adaptar el modelo de dominio al modelo de
 * persistencia.
 */
@RequiredArgsConstructor
@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    /**
     * UsuarioJpaRepository es una interfaz que extiende JpaRepository.
     */
    private final UsuarioJpaRepository usuarioJpaRepository;

    /**
     * Guarda una entidad de dominio Usuario en la base de datos.
     *
     * @param usuario la entidad de dominio a guardar
     * @return la entidad de dominio guardada
     */
    @Override
    public Usuario save(final Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntityMapper
                .toUsuarioEntity(usuario);
        Usuario saveUser = UsuarioEntityMapper.toUsuario(
                usuarioJpaRepository
                        .save(usuarioEntity));
        return saveUser;
    }
}
