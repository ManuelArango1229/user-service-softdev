package com.devsoft.user_service.infraestructure.database.postgres.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones.ClienteEntity;
import com.devsoft.user_service.infraestructure.database.postgres.repository.ClienteJpaRepository;

public class ClienteRepositoryAdapterTest {

    private ClienteJpaRepository clienteJpaRepository;
    private ClienteRepositoryPort clienteRepositoryPort;

    @BeforeEach
    public void setUp() {
        clienteJpaRepository = Mockito.mock(ClienteJpaRepository.class);
        clienteRepositoryPort = new ClienteRepositoryAdapter(clienteJpaRepository);
    }

    @Test
    public void save_shouldReturnSavedCliente() {
        // Arrange
        Cliente cliente = new Cliente(
                "123456789",
                "Juan",
                "juan@example.com",
                "12345678Abc*",
                30,
                "Calle Falsa 123",
                "Masculino",
                "3200000000"
        );

        ClienteEntity clienteEntity = new ClienteEntity(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getEmail().getValue(),
                cliente.getPassword().getValue(),
                cliente.getEdad(),
                cliente.getDireccion(),
                cliente.getGenero(),
                cliente.getTelefono()
        );

        Mockito.when(clienteJpaRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(clienteEntity);

        // Act
        Cliente result = clienteRepositoryPort.save(cliente);

        // Assert
        assertEquals(cliente.getDni(), result.getDni());
        assertEquals(cliente.getNombre(), result.getNombre());
        assertEquals(cliente.getEmail().getValue(), result.getEmail().getValue());
        assertEquals(cliente.getPassword().getValue(), result.getPassword().getValue());
        assertEquals(cliente.getEdad(), result.getEdad());
        assertEquals(cliente.getDireccion(), result.getDireccion());
        assertEquals(cliente.getGenero(), result.getGenero());
        assertEquals(cliente.getTelefono(), result.getTelefono());
    }

    @Test
    public void findByEmail_shouldReturnClienteIfExists() {
        // Arrange
        String email = "juan@example.com";
        ClienteEntity clienteEntity = new ClienteEntity(
                "123456789",
                "Juan",
                email,
                "12345678Abc*",
                30,
                "Calle Falsa 123",
                "Masculino",
                "3200000000"
        );

        Mockito.when(clienteJpaRepository.findByEmail(email)).thenReturn(Optional.of(clienteEntity));

        // Act
        Optional<Cliente> result = clienteRepositoryPort.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
        assertEquals(email, result.get().getEmail().getValue());
    }

    @Test
    public void findByDni_shouldReturnClienteIfExists() {
        // Arrange
        String dni = "123456789";
        ClienteEntity clienteEntity = new ClienteEntity(
                dni,
                "Juan",
                "juan@example.com",
                "12345678Abc*",
                30,
                "Calle Falsa 123",
                "Masculino",
                "3200000000"
        );

        Mockito.when(clienteJpaRepository.findByDni(dni)).thenReturn(clienteEntity);

        // Act
        Optional<Cliente> result = clienteRepositoryPort.findByDni(dni);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni());
        assertEquals("Juan", result.get().getNombre());
        assertEquals("juan@example.com", result.get().getEmail().getValue());
    }
}
