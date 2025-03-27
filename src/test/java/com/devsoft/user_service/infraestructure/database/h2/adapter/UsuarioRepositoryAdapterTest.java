package com.devsoft.user_service.infraestructure.database.h2.adapter;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.h2.repository.UsuarioJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsuarioRepositoryAdapterTest {

    @Autowired
    private UsuarioRepositoryAdapter adapter;

    @Autowired
    private UsuarioJpaRepository jpaRepository;

    private UsuarioEntity buildUsuarioEntity(String dni, String email) {
        return UsuarioEntity.builder()
                .dni(dni)
                .nombre("Test User")
                .email(email)
                .password("Abcde123$")
                .rol(Role.CLIENTE)
                .build();
    }

    @Test
    void shouldSaveUsuario() {
        UsuarioEntity entity = buildUsuarioEntity("123", "save@test.com");
        jpaRepository.save(entity);

        UsuarioEntity found = jpaRepository.findByDni("123");
        assertNotNull(found);
        assertEquals("123", found.getDni());
    }

    @Test
    void shouldFindUsuarioByEmail() {
        UsuarioEntity entity = buildUsuarioEntity("456", "email@test.com");
        jpaRepository.save(entity);

        Optional<Usuario> result = adapter.findByEmail("email@test.com");
        assertTrue(result.isPresent());
        assertEquals("456", result.get().getDni());
    }

    @Test
    void shouldFindUsuarioByDni() {
        UsuarioEntity entity = buildUsuarioEntity("789", "dni@test.com");
        jpaRepository.save(entity);

        Optional<Usuario> result = adapter.findByDni("789");
        assertTrue(result.isPresent());
        assertEquals("789", result.get().getDni());
    }

    @Test
    void shouldDeleteUsuarioByDni() {
        UsuarioEntity entity = buildUsuarioEntity("000", "delete@test.com");
        jpaRepository.save(entity);

        assertNotNull(jpaRepository.findByDni("000"));

        adapter.deleteByDni("000");

        UsuarioEntity deleted = jpaRepository.findByDni("000");
        assertNull(deleted);
    }
}
