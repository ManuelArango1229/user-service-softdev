package com.devsoft.user_service.infraestructure.rest.controller;

import com.devsoft.user_service.infraestructure.rest.dto.AutenticacionResponseDto;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioLoginRequestDto;
import com.devsoft.user_service.use_cases.UsuarioLoginInteractor;
import com.devsoft.user_service.use_cases.UsuarioRegistroInteractor;
import com.devsoft.user_service.use_cases.dtos.UsuarioRegisterDto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioRestControllerTest {

    @Test
    void registrarUsuario_DeberiaRetornar201SiEsExitoso() {
        // Arrange
        UsuarioRegistroInteractor mockRegistroInteractor = mock(UsuarioRegistroInteractor.class);
        UsuarioLoginInteractor mockLoginInteractor = mock(UsuarioLoginInteractor.class);

        UsuarioRestController controller = new UsuarioRestController(mockRegistroInteractor, mockLoginInteractor);

        UsuarioRegisterDto usuario = new UsuarioRegisterDto(
            "12345678",             // dni
            "Juan Pérez",           // nombre
            "juan@example.com",     // email
            "password123",          // password
            "vehiculo001",          // vehiculoAsignado
            "REPARTIDOR",           // role
            30,                     // edad
            "Calle Falsa 123",      // address
            "Masculino",            // genero
            "1234567890"            // phoneNumber
        );

        // Act
        ResponseEntity<?> response = controller.registrarUsuario(usuario);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuario registrado exitosamente.", response.getBody());
        verify(mockRegistroInteractor).execute(usuario);
    }

    @Test
    void loginUsuario_DeberiaRetornarTokenSiEsExitoso() throws Exception {
        // Arrange
        UsuarioRegistroInteractor mockRegistroInteractor = mock(UsuarioRegistroInteractor.class);
        UsuarioLoginInteractor mockLoginInteractor = mock(UsuarioLoginInteractor.class);

        String email = "juan@example.com";
        String password = "password123";
        String fakeToken = "mocked-jwt-token";

        when(mockLoginInteractor.login(email, password)).thenReturn(fakeToken);

        UsuarioRestController controller = new UsuarioRestController(mockRegistroInteractor, mockLoginInteractor);
        UsuarioLoginRequestDto loginRequest = new UsuarioLoginRequestDto(email, password);

        // Act
        AutenticacionResponseDto response = controller.loginUsuario(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(fakeToken, response.getToken());
        verify(mockLoginInteractor).login(email, password);
    }
}
