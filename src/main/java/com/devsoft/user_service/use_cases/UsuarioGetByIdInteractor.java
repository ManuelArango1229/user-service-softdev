package com.devsoft.user_service.use_cases;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
// import com.devsoft.user_service.infraestructure.rest.dto.UsuarioResponseDto;
import com.devsoft.user_service.use_cases.exceptions.UsuarioNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.devsoft.user_service.use_cases.dtos.UsuarioResponseDto;

/**
 * Interactor para obtener un usuario por su DNI.
 * <p>
 * Esta clase maneja la lógica de negocio para buscar un usuario en el sistema
 * utilizando su DNI.
 * </p>
 *
 * <p>
 * <strong>Componentes utilizados:</strong>
 * </p>
 * <ul>
 * <li>{@link UsuarioRepositoryPort} - Accede y manipula los datos del usuario
 * en el repositorio.</li>
 * <li>{@link ClienteRepositoryPort} - Accede a los datos específicos de clientes.</li>
 * <li>{@link RepartidorRepositoryPort} - Accede a los datos específicos de repartidores.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class UsuarioGetByIdInteractor {
    /**
     * Repositorio de usuarios.
     */
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    /**
     * Repositorio de clientes.
     */
    private final ClienteRepositoryPort clienteRepositoryPort;
    /**
     * Repositorio de repartidores.
     */
    private final RepartidorRepositoryPort repartidorRepositoryPort;

    /**
     * Método para obtener un usuario por su DNI.
     *
     * @param dni DNI del usuario a buscar.
     * @return Usuario encontrado como {@link UsuarioResponseDto}.
     * @throws UsuarioNoEncontradoException Si no se encuentra el usuario con el DNI proporcionado.
     */
    public UsuarioResponseDto execute(final String dni) {
        Usuario usuario = usuarioRepositoryPort.findByDni(dni)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con DNI: " + dni));

        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setDni(usuario.getDni());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail().getValue());
        dto.setRole(usuario.getRole().name());

        switch (usuario.getRole()) {
            case CLIENTE -> {
                Cliente cliente = clienteRepositoryPort.findByDni(dni)
                        .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado con DNI: " + dni));
                dto.setEdad(cliente.getEdad());
                dto.setDireccion(cliente.getDireccion());
                dto.setGenero(cliente.getGenero());
                dto.setTelefono(cliente.getTelefono());
            }
            case REPARTIDOR -> {
                Repartidor repartidor = repartidorRepositoryPort.findByDni(dni)
                        .orElseThrow(() -> new UsuarioNoEncontradoException("Repartidor no encontrado con DNI: " + dni));
                dto.setMetodoAsignado(repartidor.getMetodoAsignado());
            }
            default -> {
                // Para ADMIN u otros, no se añaden campos extra
            }
        }

        return dto;
    }
}

