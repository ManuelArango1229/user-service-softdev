package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.repositories.AdministradorRepositoryPort;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import com.devsoft.user_service.domain.value_objects.Email;
import com.devsoft.user_service.use_cases.dtos.UsuarioUpdateDto;
import com.devsoft.user_service.use_cases.exceptions.UsuarioNoEncontradoException;

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
     * REPOSITORIO DE CLIENTE.
     */
    private final ClienteRepositoryPort clienteRepository;
    /**
     * REPOSITORIO DE ADMINISTRADORES.
     */
    private final AdministradorRepositoryPort administradorRepository;
    /**
     * REPOSITORIO DE REPARTIDORES.
     */
    private final RepartidorRepositoryPort repartidorRepository;
    /**
     * REPOSITORIO DE REPARTIDORES.
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
     * @throws UsuarioNoEncontradoException Si el usuario no es encontrado o si el
     *                                      email ya está
     *                                      en uso.
     */
    public Usuario execute(final String userId, final UsuarioUpdateDto updatedData) {
        Usuario existingUser = usuarioRepository.findByDni(userId)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        switch (existingUser.getRole()) {
            case ADMINISTRADOR:
                Administrador administradorUpdate = administradorRepository.findByDni(userId).orElseThrow();
                if (updatedData.getNombre() != null) {
                    administradorUpdate.setNombre(updatedData.getNombre());
                }
                if (updatedData.getEmail() != null) {
                    administradorUpdate.setEmail(new Email(updatedData.getEmail()));
                }
                if (updatedData.getPassword() != null) {
                    administradorUpdate.setPassword(passwordEncoder.encode(updatedData.getPassword()));
                }
                return administradorRepository.save(administradorUpdate);
            case CLIENTE:
                Cliente clienteUpdate = clienteRepository.findByDni(userId).orElseThrow();
                if (updatedData.getNombre() != null) {
                    clienteUpdate.setNombre(updatedData.getNombre());
                }
                if (updatedData.getEmail() != null) {
                    clienteUpdate.setEmail(new Email(updatedData.getEmail()));
                }
                if (updatedData.getPassword() != null) {
                    clienteUpdate.setPassword(passwordEncoder.encode(updatedData.getPassword()));
                }
                if (updatedData.getEdad() != 0) {
                    clienteUpdate.setEdad(updatedData.getEdad());
                }
                if (updatedData.getAddress() != null) {
                    clienteUpdate.setDireccion(updatedData.getAddress());
                }
                if (updatedData.getGenero() != null) {
                    clienteUpdate.setGenero(updatedData.getGenero());
                }
                return clienteRepository.save(clienteUpdate);
            case REPARTIDOR:
                Repartidor reparticorUpdate = repartidorRepository.findByDni(userId).orElseThrow();
                if (updatedData.getNombre() != null) {
                    reparticorUpdate.setNombre(updatedData.getNombre());
                }
                if (updatedData.getEmail() != null) {
                    reparticorUpdate.setEmail(new Email(updatedData.getEmail()));
                }
                if (updatedData.getPassword() != null) {
                    reparticorUpdate.setPassword(passwordEncoder.encode(updatedData.getPassword()));
                }
                if (updatedData.getVehiculoAsignado() != null) {
                    reparticorUpdate.setMetodoAsignado(updatedData.getVehiculoAsignado());
                }
                return repartidorRepository.save(reparticorUpdate);
            default:
                throw new RolInvalidoErrorException("Rol no válido");
        }

    }
}
