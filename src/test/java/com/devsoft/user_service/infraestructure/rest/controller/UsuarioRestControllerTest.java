package com.devsoft.user_service.infraestructure.rest.controller;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;
import com.devsoft.user_service.use_cases.UsuarioRegistroInteractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioRestController.class)
@Import(UsuarioRestControllerTest.TestSecurityConfig.class)
class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRegistroInteractor usuarioRegistroInteractor;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
            return http.build();
        }
    }

    @Test
    @DisplayName("Debe registrar un usuario exitosamente")
    void testRegistroUsuarioExitoso() throws Exception {
        UsuarioRequestDto request = new UsuarioRequestDto(
                "12345678",
                "Juan Pérez",
                "juan@example.com",
                "Clave123!",
                "CLIENTE"
        );

        Usuario mockUsuario = new Usuario(
                "12345678",
                "Juan Pérez",
                "juan@example.com",
                "Clave123!",
                "CLIENTE"
        );

        when(usuarioRegistroInteractor.save(any())).thenReturn(mockUsuario);

        mockMvc.perform(post("/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuario registrado exitosamente."));
    }
}
