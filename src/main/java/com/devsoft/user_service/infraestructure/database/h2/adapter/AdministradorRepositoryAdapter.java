package com.devsoft.user_service.infraestructure.database.h2.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.domain.repositories.AdministradorRepositoryPort;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.AdministradorEntity;
import com.devsoft.user_service.infraestructure.database.h2.mapper.AdministradorEntityMapper;
import com.devsoft.user_service.infraestructure.database.h2.repository.AdministradorJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Adaptador que implementa el puerto del repositorio de Administradores.
 * Esta clase actúa como un adaptador entre la capa de dominio y la
 * infraestructura de persistencia,
 * siguiendo el patrón de Arquitectura Hexagonal.
 *
 * <p>
 * Se encarga de convertir las entidades de dominio {@link Administrador} a
 * entidades de persistencia
 * {@link AdministradorEntity} y viceversa, utilizando el
 * {@link AdministradorEntityMapper}.
 * </p>
 *
 * @see AdministradorRepositoryPort
 * @see AdministradorEntity
 * @see AdministradorEntityMapper
 */
@RequiredArgsConstructor
@Component
public class AdministradorRepositoryAdapter implements AdministradorRepositoryPort {
    /**
     * Repositorio JPA para operaciones de persistencia de Administradores.
     */
    private final AdministradorJpaRepository administradorJpaRepository;

    /**
     * Guarda un nuevo administrador o actualiza uno existente en la base de datos.
     *
     * @param usuario El administrador a guardar o actualizar
     * @return El administrador guardado con su información actualizada
     */
    @Override
    public Administrador save(final Administrador usuario) {
        AdministradorEntity usuarioEntity = AdministradorEntityMapper.toAdministradorEntity(usuario);
        Administrador saveUser = AdministradorEntityMapper
                .toAdministrador(administradorJpaRepository.save(usuarioEntity));
        return saveUser;

    }

    /**
     * Busca un administrador por su dirección de email.
     *
     * @param email El email del administrador a buscar
     * @return Un Optional que contiene el administrador si existe, o vacío si no se
     *         encuentra
     */
    @Override
    public Optional<Administrador> findByEmail(final String email) {
        return Optional.ofNullable(administradorJpaRepository.findByEmail(email))
                .map(usuarioEntity -> AdministradorEntityMapper.toAdministrador(usuarioEntity.get()));
    }

    /**
     * Busca un administrador por su DNI.
     *
     * @param dni El DNI del administrador a buscar
     * @return Un Optional que contiene el administrador si existe, o vacío si no se
     *         encuentra
     */
    @Override
    public Optional<Administrador> findByDni(final String dni) {
        return Optional.ofNullable(administradorJpaRepository.findByDni(dni))
                .map(usuarioEntity -> AdministradorEntityMapper.toAdministrador(usuarioEntity));

    }

}
