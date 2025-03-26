package com.devsoft.user_service.infraestructure.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoft.user_service.use_cases.UsuarioUpdateInteractor;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;

import lombok.RequiredArgsConstructor;

/**
 * Este controlador REST maneja las solicitudes de actualización de usuarios.
 *
 */
@RequiredArgsConstructor
@RequestMapping("/usuario")
@RestController
public class UsuarioUpdateRestController {
    /**
     * Servicio de interacción para la actualización del usuario.
     */
    private final UsuarioUpdateInteractor usuarioUpdateInteractor;

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
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable final String id,
            @RequestBody final UsuarioUpdateDto usuarioUpdateDto) {
        usuarioUpdateInteractor.execute(id, usuarioUpdateDto);
        return ResponseEntity.ok("Usuario actualizado exitosamente.");
    }

}
