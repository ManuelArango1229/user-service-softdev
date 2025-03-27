package com.devsoft.user_service.infraestructure.database.h2.adapter;

import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.AdministradorEntity;
import com.devsoft.user_service.infraestructure.database.h2.repository.AdministradorJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdministradorRepositoryAdapterTest {

    @Autowired
    private AdministradorRepositoryAdapter adapter;

    @Autowired
    private AdministradorJpaRepository jpaRepository;

    private AdministradorEntity buildAdministradorEntity(String dni, String email) {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setDni(dni);
        entity.setNombre("Admin Test");
        entity.setEmail(email);
        entity.setPassword("Abcde123$");
        entity.setRol(Role.ADMINISTRADOR);
        return entity;
    }
    

    @Test
    void shouldSaveAdministrador() {
        AdministradorEntity entity = buildAdministradorEntity("101", "save@admin.com");
        jpaRepository.save(entity);

        AdministradorEntity found = jpaRepository.findByDni("101");
        assertNotNull(found);
    }

    @Test
    void shouldFindAdministradorByEmail() {
        AdministradorEntity entity = buildAdministradorEntity("102", "admin@email.com");
        jpaRepository.save(entity);

        Optional<Administrador> result = adapter.findByEmail("admin@email.com");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindAdministradorByDni() {
        AdministradorEntity entity = buildAdministradorEntity("103", "dni@admin.com");
        jpaRepository.save(entity);

        Optional<Administrador> result = adapter.findByDni("103");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldThrowWhenAdministradorNotFoundByEmail() {
        assertThrows(NoSuchElementException.class, () -> {
            adapter.findByEmail("no@admin.com");
        });
}

    @Test
    void shouldReturnEmptyWhenAdministradorNotFoundByDni() {
        Optional<Administrador> result = adapter.findByDni("999");
        assertTrue(result.isEmpty());
    }
}
