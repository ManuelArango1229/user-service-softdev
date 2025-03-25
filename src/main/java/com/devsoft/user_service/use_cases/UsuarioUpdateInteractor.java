package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import com.devsoft.user_service.domain.value_objects.Email;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;

import lombok.RequiredArgsConstructor;

/**
 * Interactor para la actualización de usuarios.
 *
 * <p>
 * Esta clase maneja la lógica de negocio para actualizar los datos de un
 * usuario existente.
 * </p>
 *
 * <p>
 * <strong>Componentes utilizados:</strong>
 * </p>
 * <ul>
 * <li>{@link UsuarioRepositoryPort} - Accede y manipula los datos del usuario
 * en el repositorio.</li>
 * <li>{@link PasswordEncoderPort} - Codifica la nueva contraseña del usuario si
 * es proporcionada.</li>
 * </ul>
 *
 * <p>
 * <strong>Ejemplo de uso:</strong>
 * </p>
 *
 * <pre>{@code
 * UsuarioUpdateInteractor interactor = new UsuarioUpdateInteractor(usuarioRepository, passwordEncoder);
 * UsuarioUpdateDto updatedData = new UsuarioUpdateDto("Juan Pérez", "juan.perez@ejemplo.com", "nuevaContraseña123");
 * Usuario updatedUser = interactor.execute("12345678", updatedData);
 * }</pre>
 */
@RequiredArgsConstructor
@Component
public class UsuarioUpdateInteractor {
    /**
     * REPOSITORIO DE USUARIOS.
     */
    private final UsuarioRepositoryPort usuarioRepository;
    /**
     * PASSWORD ENCODER.
     */
    private final PasswordEncoderPort passwordEncoder;

    /**
     * Actualiza los detalles de un usuario existente.
     *
     * @param userId      El ID del usuario a actualizar.
     * @param updatedData Los nuevos datos del usuario encapsulados en un
     *                    {@link UsuarioUpdateDto}.
     * @return El usuario actualizado.
     * @throws RuntimeException Si el usuario no es encontrado o si el email ya está
     *                          en uso.
     */
    public Usuario execute(final String userId, final UsuarioUpdateDto updatedData) {
        Usuario user = usuarioRepository.findByDni(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (updatedData.getEmail() != null) {
            user.setEmail(new Email(updatedData.getEmail()));
        }

        if (updatedData.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        }

        if (updatedData.getNombre() != null) {
            user.setNombre(updatedData.getNombre());
        }

        return usuarioRepository.save(user);
    }
}
