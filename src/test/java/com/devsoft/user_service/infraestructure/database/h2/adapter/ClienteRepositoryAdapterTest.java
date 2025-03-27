package com.devsoft.user_service.infraestructure.database.h2.adapter;

import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones.ClienteEntity;
import com.devsoft.user_service.infraestructure.database.h2.repository.ClienteJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClienteRepositoryAdapterTest {

    @Autowired
    private ClienteRepositoryAdapter adapter;

    @Autowired
    private ClienteJpaRepository jpaRepository;

    private ClienteEntity buildClienteEntity(String dni, String email) {
        ClienteEntity entity = new ClienteEntity();
        entity.setDni(dni);
        entity.setNombre("Cliente Test");
        entity.setEmail(email);
        entity.setPassword("Abcde123$");
        entity.setRol(Role.CLIENTE);
        return entity;
    }

    @Test
    void shouldSaveCliente() {
        ClienteEntity entity = buildClienteEntity("201", "save@cliente.com");
        jpaRepository.save(entity);

        ClienteEntity found = jpaRepository.findByDni("201");
        assertNotNull(found);
    }

    @Test
    void shouldFindClienteByEmail() {
        ClienteEntity entity = buildClienteEntity("202", "cliente@email.com");
        jpaRepository.save(entity);

        Optional<Cliente> result = adapter.findByEmail("cliente@email.com");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindClienteByDni() {
        ClienteEntity entity = buildClienteEntity("203", "dni@cliente.com");
        jpaRepository.save(entity);

        Optional<Cliente> result = adapter.findByDni("203");
        assertTrue(result.isPresent());
    }

    @Test
    void shouldThrowWhenClienteNotFoundByEmail() {
        assertThrows(NoSuchElementException.class, () -> {
            adapter.findByEmail("no@cliente.com");
        });
    }


    @Test
    void shouldReturnEmptyWhenClienteNotFoundByDni() {
        Optional<Cliente> result = adapter.findByDni("999");
        assertTrue(result.isEmpty());
    }
}
