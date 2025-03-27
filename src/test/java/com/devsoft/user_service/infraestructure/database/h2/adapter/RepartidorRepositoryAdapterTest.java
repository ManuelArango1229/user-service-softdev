package com.devsoft.user_service.infraestructure.database.h2.adapter;

import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.RepartidorEntity;
import com.devsoft.user_service.infraestructure.database.h2.repository.RepartidorJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepartidorRepositoryAdapterTest {

    @Autowired
    private RepartidorRepositoryAdapter adapter;

    @Autowired
    private RepartidorJpaRepository jpaRepository;

    private RepartidorEntity buildRepartidorEntity(String dni, String email) {
        RepartidorEntity entity = new RepartidorEntity();
        entity.setDni(dni);
        entity.setNombre("Repartidor Test");
        entity.setEmail(email);
        entity.setPassword("Abcde123$");
        entity.setRol(Role.REPARTIDOR);
        return entity;
    }
    

    @Test
    void shouldSaveRepartidor() {
        RepartidorEntity entity = buildRepartidorEntity("301", "save@repartidor.com");
        jpaRepository.save(entity);

        RepartidorEntity found = jpaRepository.findByDni("301");
        assertNotNull(found);
    }

    @Test
    void shouldFindRepartidorByEmail() {
        RepartidorEntity entity = buildRepartidorEntity("302", "repartidor@email.com");
        jpaRepository.save(entity);

        Optional<Repartidor> result = adapter.findByEmail("repartidor@email.com");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindRepartidorByDni() {
        RepartidorEntity entity = buildRepartidorEntity("303", "dni@repartidor.com");
        jpaRepository.save(entity);

        Optional<Repartidor> result = adapter.findByDni("303");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldThrowWhenRepartidorNotFoundByEmail() {
        assertThrows(NoSuchElementException.class, () -> {
            adapter.findByEmail("no@repartidor.com");
        });
    }

    @Test
    void shouldReturnEmptyWhenRepartidorNotFoundByDni() {
        Optional<Repartidor> result = adapter.findByDni("999");
        assertTrue(result.isEmpty());
    }
}
