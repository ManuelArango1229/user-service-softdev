package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Service;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
import com.devsoft.user_service.use_cases.dtos.UsuarioResponseDto;
import com.devsoft.user_service.use_cases.exceptions.UsuarioNoEncontradoException;

import lombok.RequiredArgsConstructor;

/**
 * Interactor para obtener un usuario por su correo electrónico.
 * <p>
 * Esta clase maneja la lógica de negocio para buscar un usuario en el sistema
 * utilizando su correo electrónico.
 * </p>
 *
 * <p>
 * <strong>Componentes utilizados:</strong>
 * </p>
 * <ul>
 * <li>{@link UsuarioRepositoryPort} - Accede y manipula los datos del usuario
 * en el repositorio.</li>
 * </ul>
**/
@RequiredArgsConstructor
@Service
public class UsuarioGetByEmailInteractor {

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
     * Método para obtener un usuario por su correo electrónico.
     *
     * @param email Correo electrónico del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
    **/
    public UsuarioResponseDto execute(final String email) {
        Usuario usuario = usuarioRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con el correo: " + email));

        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setDni(usuario.getDni());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail().getValue());
        dto.setRole(usuario.getRole().name());

        switch (usuario.getRole()) {
            case CLIENTE -> {
                Cliente cliente = clienteRepositoryPort.findByEmail(email)
                        .orElseThrow(() -> new UsuarioNoEncontradoException("Cliente no encontrado"));
                dto.setEdad(cliente.getEdad());
                dto.setDireccion(cliente.getDireccion());
                dto.setGenero(cliente.getGenero());
                dto.setTelefono(cliente.getTelefono());
            }
            case REPARTIDOR -> {
                Repartidor repartidor = repartidorRepositoryPort.findByEmail(email)
                        .orElseThrow(() -> new UsuarioNoEncontradoException("Repartidor no encontrado"));
                dto.setMetodoAsignado(repartidor.getMetodoAsignado());
            }
            default -> {
                // Nada extra para ADMIN o cualquier otro
            }
        }
        return dto;
    }
}
