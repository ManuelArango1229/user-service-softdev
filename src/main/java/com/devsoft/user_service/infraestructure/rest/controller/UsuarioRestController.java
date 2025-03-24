package com.devsoft.user_service.infraestructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.infraestructure.rest.dto.AutenticacionResponseDto;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioLoginRequestDto;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioRequestDto;
import com.devsoft.user_service.infraestructure.rest.mapper.UsuarioDtoMapper;
import com.devsoft.user_service.use_cases.UsuarioLoginInteractor;
import com.devsoft.user_service.use_cases.UsuarioRegistroInteractor;
import com.devsoft.user_service.use_cases.UsuarioUpdateInteractor;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;

import jakarta.validation.Valid;

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
     * Servicio de interacción para el inicio de sesión de usuarios.
     */
    private final UsuarioLoginInteractor usuarioLoginInteractor;

    /**
     * Servicio de interacción para la actualización del usuario.
     */
    private final UsuarioUpdateInteractor usuarioUpdateInteractor;

    /**
     * Registra un nuevo usuario.
     *
     * @param usuarioDto el objeto de transferencia de datos del usuario que
     *                   contiene la información del usuario
     *                   Registra un nuevo usuario en el sistema.
     * @return ResponseEntity con estado 201 (CREATED) si el registro es exitoso, o
     *         400 (BAD REQUEST) en caso de error.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody final UsuarioRequestDto usuarioDto) {
        try {
            Usuario user = UsuarioDtoMapper.mapToEntity(usuarioDto);
            usuarioRegistroInteractor.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Maneja la solicitud de inicio de sesión de un usuario.
     *
     * @param request Objeto que contiene los datos de inicio de sesión del usuario.
     * @return Objeto que contiene la respuesta de autenticación.
     * @throws Exception si hay un error en la autenticación.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/login")
    public AutenticacionResponseDto loginUsuario(@RequestBody final UsuarioLoginRequestDto request) throws Exception {
        return new AutenticacionResponseDto(usuarioLoginInteractor.login(request.getEmail(), request.getPassword()));
    }

    /**
     * Interactor para la actualización de usuarios.
     * <p>
     * Permite modificar datos de un usuario autenticado, incluyendo nombre, correo
     * y contraseña, validando reglas de negocio.
     * </p>
     *
     * <p>
     * Usa {@link UsuarioRepositoryPort} para la persistencia y
     * {@link PasswordEncoderPort}
     * para codificar la nueva contraseña si es proporcionada.
     * </p>
     *
     * <pre>{@code
     * UsuarioUpdateInteractor interactor = new UsuarioUpdateInteractor(usuarioRepository, passwordEncoder);
     * UsuarioUpdateDto updatedData = new UsuarioUpdateDto("Juan Pérez", "juan.perez@ejemplo.com",
     *         "nuevaContraseña123");
     * Usuario updatedUser = interactor.execute("12345678", updatedData);
     * }</pre>
     *
     * @param id               Identificador del usuario autenticado.
     * @param usuarioUpdateDto Datos a actualizar del usuario.
     * @return Respuesta de éxito.
     */
    @PutMapping("/usuarios/{id}")
    @PreAuthorize("#id == authentication.principal.username")
    public ResponseEntity<String> actualizarUsuario(@PathVariable final String id,
            @RequestBody final UsuarioUpdateDto usuarioUpdateDto) {
        usuarioUpdateInteractor.execute(id, usuarioUpdateDto);
        return ResponseEntity.ok("Usuario actualizado exitosamente.");
    }

}
