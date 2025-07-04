package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.infraestructure.database.postgres.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.postgres.mapper.UsuarioEntityMapper;
import com.devsoft.user_service.infraestructure.database.postgres.repository.UsuarioJpaRepository;

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
@Profile("postgres")
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    /**
     * Repositorio JPA para operaciones de persistencia de Repartidores.
     */
    private final UsuarioJpaRepository usuarioJpaRepository;

    /**
     * Guarda un nuevo repartidor o actualiza uno existente en la base de datos.
     *
     * @param usuario El repartidor a guardar o actualizar
     * @return El repartidor guardado con su información actualizada
     */
    @Override
    public Usuario save(final Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntityMapper.toUsuarioEntity(usuario);
        Usuario saveUser = UsuarioEntityMapper
                .toUsuario(usuarioJpaRepository.save(usuarioEntity));
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
    public Optional<Usuario> findByEmail(final String email) {
        return usuarioJpaRepository.findByEmail(email)  // Esto es Optional<UsuarioEntity>
                .map(UsuarioEntityMapper::toUsuario);   // Mapea directamente si existe
    }


    /**
     * Busca un repartidor por su DNI.
     *
     * @param dni El DNI del repartidor a buscar
     * @return Un Optional que contiene el repartidor si existe, o vacío si no se
     *         encuentra
     */
    @Override
    public Optional<Usuario> findByDni(final String dni) {
        return Optional.ofNullable(usuarioJpaRepository.findByDni(dni))
                .map(usuarioEntity -> UsuarioEntityMapper.toUsuario(usuarioEntity));

    }

    /**
     * Elimina un usuario de la base de datos por su DNI.
     *
     * @param dni El DNI del repartidor a eliminar
     */
    @Override
    public void deleteByDni(final String dni) {
        usuarioJpaRepository.deleteByDni(dni);
    }
}
