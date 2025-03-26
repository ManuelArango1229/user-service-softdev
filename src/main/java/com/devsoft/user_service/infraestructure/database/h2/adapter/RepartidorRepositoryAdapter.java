package com.devsoft.user_service.infraestructure.database.h2.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.RepartidorEntity;
import com.devsoft.user_service.infraestructure.database.h2.mapper.RepartidorEntityMapper;
import com.devsoft.user_service.infraestructure.database.h2.repository.RepartidorJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Adaptador que implementa el puerto del repositorio de Repartidores.
 * Esta clase actúa como un adaptador entre la capa de dominio y la
 * infraestructura de persistencia,
 * siguiendo el patrón de Arquitectura Hexagonal.
 *
 * <p>
 * Se encarga de convertir las entidades de dominio {@link Repartidor} a
 * entidades de persistencia
 * {@link RepartidorEntity} y viceversa, utilizando el
 * {@link RepartidorEntityMapper}.
 * </p>
 *
 * @see RepartidorRepositoryPort
 * @see RepartidorEntity
 * @see RepartidorEntityMapper
 */
@RequiredArgsConstructor
@Component
public class RepartidorRepositoryAdapter implements RepartidorRepositoryPort {
    /**
     * Repositorio JPA para operaciones de persistencia de Repartidores.
     */
    private final RepartidorJpaRepository repartidorJpaRepository;

    /**
     * Guarda un nuevo repartidor o actualiza uno existente en la base de datos.
     *
     * @param usuario El repartidor a guardar o actualizar
     * @return El repartidor guardado con su información actualizada
     */
    @Override
    public Repartidor save(final Repartidor usuario) {
        RepartidorEntity usuarioEntity = RepartidorEntityMapper.toRepartidorEntity(usuario);
        Repartidor saveUser = RepartidorEntityMapper
                .toRepartidor(repartidorJpaRepository.save(usuarioEntity));
        return saveUser;

    }

    /**
     * Busca un repartidor por su dirección de email.
     *
     * @param email El email del repartidor a buscar
     * @return Un Optional que contiene el repartidor si existe, o vacío si no se
     *         encuentra
     */
    @Override
    public Optional<Repartidor> findByEmail(final String email) {
        return Optional.ofNullable(repartidorJpaRepository.findByEmail(email))
                .map(usuarioEntity -> RepartidorEntityMapper.toRepartidor(usuarioEntity.get()));
    }

    /**
     * Busca un repartidor por su DNI.
     *
     * @param dni El DNI del repartidor a buscar
     * @return Un Optional que contiene el repartidor si existe, o vacío si no se
     *         encuentra
     */
    @Override
    public Optional<Repartidor> findByDni(final String dni) {
        return Optional.ofNullable(repartidorJpaRepository.findByDni(dni))
                .map(usuarioEntity -> RepartidorEntityMapper.toRepartidor(usuarioEntity));

    }

}
