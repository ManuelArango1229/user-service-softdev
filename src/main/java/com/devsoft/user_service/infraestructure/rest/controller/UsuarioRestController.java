package com.devsoft.user_service.infraestructure.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;
import com.devsoft.user_service.infraestructure.rest.mapper.UsuarioDtoMapper;
import com.devsoft.user_service.use_cases.UsuarioRegistroInteractor;

import lombok.RequiredArgsConstructor;

/**
 * UsuarioRestController es un controlador REST que maneja las solicitudes de
 * registro de usuarios.
 * Utiliza {@link UsuarioRegistroInteractor} para guardar los datos del usuario.
 */
@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class UsuarioRestController {
    /**
     * Servicio de interacción para el registro de usuarios.
     */
    private final UsuarioRegistroInteractor usuarioRegistroInteractor;

    /**
     * Registra un nuevo usuario.
     *
     * @param usuarioDto el objeto de transferencia de datos del usuario que
     *                   contiene la información del usuario
     */
    @PostMapping("/registro")
    public void registrarUsuario(
            @RequestBody final UsuarioRequestDto usuarioDto) {
        Usuario user = UsuarioDtoMapper.mapToEntity(usuarioDto);
        usuarioRegistroInteractor.save(user);
    }
}
