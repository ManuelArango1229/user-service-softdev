package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Service;

import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Clase que implementa el caso de uso para eliminar un usuario.
 */
@Service
@RequiredArgsConstructor
public class UsuarioDeleteInteractor {
    /**
     * Repositorio de usuarios.
     */
    private final UsuarioRepositoryPort usuarioRepository;

    /**
     * Método que permite eliminar un usuario por su DNI.
     *
     * @param dni el DNI del usuario a eliminar
     */
    @Transactional
    public void eliminarUsuarioPorDni(final String dni) {
        if (!usuarioRepository.findByDni(dni).isPresent()) {
            throw new IllegalArgumentException("El usuario con DNI " + dni + " no existe.");
        }
        usuarioRepository.deleteByDni(dni);
    }
}
