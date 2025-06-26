package com.devsoft.user_service.infraestructure.rest.controller;

import com.devsoft.user_service.infraestructure.rest.dto.RepartidorResponseDto;
import com.devsoft.user_service.use_cases.*;
import com.devsoft.user_service.use_cases.dtos.UsuarioResponseDto;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioUpdateRestControllerTest {

    @Test
    void actualizarUsuario_DeberiaRetornar200() {
        var mockUpdate = mock(UsuarioUpdateInteractor.class);

        var controller = new UsuarioUpdateRestController(
                mock(UsuarioDeleteInteractor.class),
                mockUpdate,
                mock(UsuarioGetByEmailInteractor.class),
                mock(ObtenerRepartidoresInteractor.class),
                mock(UsuarioGetByIdInteractor.class)
        );

        var updateDto = mock(UsuarioUpdateDto.class); // sin constructor

        var response = controller.actualizarUsuario("12345678", updateDto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Usuario actualizado exitosamente.", response.getBody());
        verify(mockUpdate).execute("12345678", updateDto);
    }

    @Test
    void eliminarUsuario_DeberiaRetornar200() {
        var mockDelete = mock(UsuarioDeleteInteractor.class);

        var controller = new UsuarioUpdateRestController(
                mockDelete,
                mock(UsuarioUpdateInteractor.class),
                mock(UsuarioGetByEmailInteractor.class),
                mock(ObtenerRepartidoresInteractor.class),
                mock(UsuarioGetByIdInteractor.class)
        );

        var response = controller.eliminarUsuario("12345678");

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Usuario eliminado exitosamente.", response.getBody());
        verify(mockDelete).eliminarUsuarioPorDni("12345678");
    }

    @Test
    void buscarUsuarioPorEmail_DeberiaRetornarUsuario() {
        var mockGetEmail = mock(UsuarioGetByEmailInteractor.class);
        var usuarioMock = mock(UsuarioResponseDto.class);

        when(mockGetEmail.execute("correo@test.com")).thenReturn(usuarioMock);

        var controller = new UsuarioUpdateRestController(
                mock(UsuarioDeleteInteractor.class),
                mock(UsuarioUpdateInteractor.class),
                mockGetEmail,
                mock(ObtenerRepartidoresInteractor.class),
                mock(UsuarioGetByIdInteractor.class)
        );

        var response = controller.buscarUsuarioPorEmail("correo@test.com");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(usuarioMock, response.getBody());
    }

    @Test
    void obtenerRepartidores_DeberiaRetornarLista() {
        var repartidor1 = mock(RepartidorResponseDto.class);
        var repartidor2 = mock(RepartidorResponseDto.class);
        var lista = List.of(repartidor1, repartidor2);

        var mockRepartidores = mock(ObtenerRepartidoresInteractor.class);
        when(mockRepartidores.execute()).thenReturn(lista);

        var controller = new UsuarioUpdateRestController(
                mock(UsuarioDeleteInteractor.class),
                mock(UsuarioUpdateInteractor.class),
                mock(UsuarioGetByEmailInteractor.class),
                mockRepartidores,
                mock(UsuarioGetByIdInteractor.class)
        );

        var response = controller.obtenerRepartidores();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(lista, response.getBody());
    }

    @Test
    void obtenerUsuarioPorDni_DeberiaRetornarUsuario() {
        var usuarioMock = mock(UsuarioResponseDto.class);

        var mockGetId = mock(UsuarioGetByIdInteractor.class);
        when(mockGetId.execute("12345678")).thenReturn(usuarioMock);

        var controller = new UsuarioUpdateRestController(
                mock(UsuarioDeleteInteractor.class),
                mock(UsuarioUpdateInteractor.class),
                mock(UsuarioGetByEmailInteractor.class),
                mock(ObtenerRepartidoresInteractor.class),
                mockGetId
        );

        var response = controller.obtenerUsuarioPorId("12345678");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(usuarioMock, response.getBody());
    }
}
