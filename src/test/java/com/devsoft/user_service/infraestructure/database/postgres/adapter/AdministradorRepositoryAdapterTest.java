package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.AdministradorEntity;
import com.devsoft.user_service.infraestructure.database.postgres.mapper.AdministradorEntityMapper;
import com.devsoft.user_service.infraestructure.database.postgres.repository.AdministradorJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdministradorRepositoryAdapterTest {

    private AdministradorJpaRepository repository;
    private AdministradorRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        repository = mock(AdministradorJpaRepository.class);
        adapter = new AdministradorRepositoryAdapter(repository);
    }

    @Test
    void save_shouldReturnSavedAdministrador() {
        Administrador admin = new Administrador("123", "Admin", "admin@email.com", "Admin123");
        AdministradorEntity entity = AdministradorEntityMapper.toAdministradorEntity(admin);

        when(repository.save(any())).thenReturn(entity);

        Administrador result = adapter.save(admin);
        assertEquals(admin.getDni(), result.getDni());
    }

    @Test
    void findByEmail_shouldReturnAdministradorIfExists() {
        AdministradorEntity entity = new AdministradorEntity("123", "Admin", "admin@email.com", "Admin123");
        when(repository.findByEmail("admin@email.com")).thenReturn(Optional.of(entity));

        Optional<Administrador> result = adapter.findByEmail("admin@email.com");
        assertTrue(result.isPresent());
        assertEquals("123", result.get().getDni());
    }

    @Test
    void findByDni_shouldReturnAdministradorIfExists() {
        AdministradorEntity entity = new AdministradorEntity("123", "Admin", "admin@email.com", "Admin123");
        when(repository.findByDni("123")).thenReturn(entity);

        Optional<Administrador> result = adapter.findByDni("123");
        assertTrue(result.isPresent());
        assertEquals("123", result.get().getDni());
    }
}
