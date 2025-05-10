package com.devsoft.user_service.infraestructure.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsoft.user_service.use_cases.UsuarioDeleteInteractor;
import com.devsoft.user_service.use_cases.UsuarioGetByEmailInteractor;
import com.devsoft.user_service.use_cases.UsuarioUpdateInteractor;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;
import com.devsoft.user_service.use_cases.dtos.UsuarioResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Este controlador REST maneja las solicitudes de actualización de usuarios.
 *
 */
@RequiredArgsConstructor
@RequestMapping("/usuario")
@RestController
public class UsuarioUpdateRestController {

    /**
     * Servicio de interacción para la eliminación de usuarios.
     */
    private final UsuarioDeleteInteractor usuarioDeleteInteractor;
    /**
     * Servicio de interacción para la actualización del usuario.
     */
    private final UsuarioUpdateInteractor usuarioUpdateInteractor;

    /**
     * Servicio de interacción para la búsqueda de usuarios por correo electrónico.
     */
    private final UsuarioGetByEmailInteractor usuarioGetByEmailInteractor;

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
    /**
     * Interactor para la eliminación de usuarios.
     * <p>
     * Permite eliminar un usuario del sistema a partir de su DNI.
     * Se verifica primero si el usuario existe antes de proceder con la eliminación.
     * </p>
     *
     * <p>
     * Usa {@link UsuarioRepositoryPort} para realizar la consulta y eliminación del usuario.
     * </p>
     *
     * <pre>{@code
     * UsuarioDeleteInteractor interactor = new UsuarioDeleteInteractor(usuarioRepository);
     * interactor.eliminarUsuarioPorDni("12345678");
     * }</pre>
     *
     * @param id DNI del usuario que se desea eliminar.
     * @throws IllegalArgumentException si el usuario con el DNI especificado no existe.
     * @return Respuesta de éxito.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable final String id) {
        usuarioDeleteInteractor.eliminarUsuarioPorDni(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente.");
    }

    /**
     * Interactor para la búsqueda de usuarios por correo electrónico.
     * <p>
     * Permite obtener los datos de un usuario a partir de su correo electrónico.
     * </p>
     *
     * <p>
     * Usa {@link UsuarioRepositoryPort} para realizar la consulta del usuario.
     * </p>
     *
     * @param email Correo electrónico del usuario a buscar.
     * @return Datos del usuario encontrado.
     */
    @GetMapping("/buscar/{email}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorEmail(@PathVariable final String email) {
        UsuarioResponseDto usuario = usuarioGetByEmailInteractor.execute(email);
        return ResponseEntity.ok(usuario);
    }
}
