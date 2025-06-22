package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.ClienteEntity;
import com.devsoft.user_service.infraestructure.database.postgres.mapper.ClienteEntityMapper;
import com.devsoft.user_service.infraestructure.database.postgres.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;

/**
 * Clase adaptadora que implementa la interfaz ClienteRepositoryPort
 * Esta clase es responsable de adaptar el modelo de dominio al modelo de
 * persistencia.
 */
@RequiredArgsConstructor
@Component
@Profile("postgres")
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {
    /**
     * ClienteJpaRepository es una interfaz que extiende JpaRepository.
     */
    private final ClienteJpaRepository clienteJpaRepository;

    /**
     * Guarda una entidad de dominio Cliente en la base de datos.
     *
     * @param usuario la entidad de dominio a guardar
     * @return la entidad de dominio guardada
     */
    @Override
    public Cliente save(final Cliente usuario) {

        ClienteEntity usuarioEntity = ClienteEntityMapper.toClienteEntity(usuario);
        Cliente saveUser = ClienteEntityMapper
                .toCliente(clienteJpaRepository.save(usuarioEntity));
        return saveUser;

    }

    /**
     * Busca un cliente por su dirección de correo electrónico y lo convierte
     * en un objeto de dominio {@code Cliente}.
     *
     * @param email la dirección de correo electrónico del cliente a buscar.
     * @return un {@code Optional<Cliente>} que contiene el cliente si existe en
     *         la base de datos, o un {@code Optional.empty()} si no se encuentra.
     */
    @Override
    public Optional<Cliente> findByEmail(final String email) {
        return Optional.ofNullable(clienteJpaRepository.findByEmail(email))
                .map(usuarioEntity -> ClienteEntityMapper.toCliente(usuarioEntity.get()));
    }

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni el DNI del cliente a buscar
     * @return la entidad de dominio Cliente
     */
    @Override
    public Optional<Cliente> findByDni(final String dni) {
        return Optional.ofNullable(clienteJpaRepository.findByDni(dni))
                .map(usuarioEntity -> ClienteEntityMapper.toCliente(usuarioEntity));
    }
}
