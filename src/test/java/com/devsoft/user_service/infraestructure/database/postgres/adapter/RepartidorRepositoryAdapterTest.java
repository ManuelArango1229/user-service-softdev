package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.RepartidorEntity;
import com.devsoft.user_service.infraestructure.database.postgres.repository.RepartidorJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RepartidorRepositoryAdapterTest {

    @Mock
    private RepartidorJpaRepository repartidorJpaRepository;

    @InjectMocks
    private RepartidorRepositoryAdapter repartidorRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnSavedRepartidor() {
        Repartidor repartidor = new Repartidor("123", "Pedro", "pedro@example.com", "Pedro1234", "BICICLETA");

        RepartidorEntity entity = new RepartidorEntity(
                repartidor.getDni(),
                repartidor.getNombre(),
                repartidor.getEmail().getValue(),
                repartidor.getPassword().getValue(),
                repartidor.getMetodoAsignado()
        );

        when(repartidorJpaRepository.save(entity)).thenReturn(entity);

        Repartidor saved = repartidorRepositoryAdapter.save(repartidor);

        assertNotNull(saved);
        assertEquals("pedro@example.com", saved.getEmail().getValue());
        assertEquals("BICICLETA", saved.getMetodoAsignado());
    }

    @Test
    void findByEmail_shouldReturnRepartidorIfExists() {
        String email = "pedro@example.com";
        RepartidorEntity entity = new RepartidorEntity("123", "Pedro", email, "Pedro1234", "BICICLETA");

        when(repartidorJpaRepository.findByEmail(email)).thenReturn(Optional.of(entity));

        Optional<Repartidor> found = repartidorRepositoryAdapter.findByEmail(email);

        assertTrue(found.isPresent());
        assertEquals(email, found.get().getEmail().getValue());
    }

    @Test
    void findByDni_shouldReturnRepartidorIfExists() {
        String dni = "123";
        RepartidorEntity entity = new RepartidorEntity(dni, "Pedro", "pedro@example.com", "Pedro1234", "BICICLETA");

        Mockito.when(repartidorJpaRepository.findByDni(dni)).thenReturn(entity);

        Optional<Repartidor> found = repartidorRepositoryAdapter.findByDni(dni);

        assertTrue(found.isPresent());
        assertEquals(dni, found.get().getDni());
    }
}
