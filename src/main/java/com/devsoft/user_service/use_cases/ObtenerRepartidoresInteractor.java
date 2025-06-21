package com.devsoft.user_service.use_cases;

// Interactor para obtener repartidores.

import org.springframework.stereotype.Component;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.infraestructure.rest.dto.RepartidorResponseDto;
import com.devsoft.user_service.use_cases.exceptions.RepartidorNoEncontradoException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import com.devsoft.user_service.domain.entities.Usuario;

/**
 * Interactor para obtener repartidores.
 *
 * <p>
 * Esta clase maneja la lógica de negocio para obtener una lista de repartidores
 * del sistema.
 * </p>
 *
 * <p>
 * Utiliza {@link RepartidorRepositoryPort} para acceder a los datos de los
 * repartidores.
 * </p>
 */
@RequiredArgsConstructor
@Component
public class ObtenerRepartidoresInteractor {
    /**
     * Repositorio de repartidores.
     */
    private final RepartidorRepositoryPort repartidorRepository;
    /**
     * Repositorio de usuarios.
     */
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    /**
     * Método para obtener todos los repartidores.
     *
     * @return Lista de repartidores como {@link RepartidorResponseDto}.
     * @throws RepartidorNoEncontradoException Si no se encuentran repartidores.
     */
    public List<RepartidorResponseDto> execute() {
        List<Repartidor> repartidores = repartidorRepository.findAll();
        if (repartidores.isEmpty()) {
            throw new RepartidorNoEncontradoException("No se encontraron repartidores.");
        }

        List<RepartidorResponseDto> responseList = new ArrayList<>();

        for (Repartidor repartidor : repartidores) {
            // Obtener usuario base por email o dni (lo que tengas de referencia)
            Usuario usuario = usuarioRepositoryPort.findByEmail(repartidor.getEmail().getValue())
                .orElseThrow(() -> new RepartidorNoEncontradoException("Usuario base no encontrado para repartidor"));

            RepartidorResponseDto dto = new RepartidorResponseDto();
            dto.setDni(usuario.getDni());
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail().getValue());
            dto.setRole(usuario.getRole().name());
            dto.setMetodoAsignado(repartidor.getMetodoAsignado());

            responseList.add(dto);
        }

        return responseList;
    }
}
