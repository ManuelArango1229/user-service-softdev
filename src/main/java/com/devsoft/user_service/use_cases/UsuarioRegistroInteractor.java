package com.devsoft.user_service.use_cases;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import com.devsoft.user_service.domain.value_objects.Password;
import com.devsoft.user_service.domain.value_objects.Role;

import lombok.RequiredArgsConstructor;

/**
 * Esta clase representa el caso de uso de registro de un usuario.
 */
@RequiredArgsConstructor
@Component
public class UsuarioRegistroInteractor {
    /**
     * respositorio de usuarios.
     */
    private final UsuarioRepositoryPort usuarioRepository;
    /**
     * servicio de encriptación de contraseñas.
     */
    private final PasswordEncoderPort passwordEncoder;

    /**
     * Constructor de la clase UsuarioRegistroInteractor.
     *
     * @param usuario
     * @return Usuario resgistrado
     */
    public Usuario save(final Usuario usuario) {
        Usuario user = usuarioRepository.findByDni(usuario.getDni()).orElse(null);
        if (user != null && user.getDni() != null) {
            throw new UsuarioExisteErrorException("El usuario con DNI " + usuario.getDni() + " ya existe.");
        }
        if (!usuario.getPassword().getValue().matches(Password.PASSWORD_PATTERN)) {
            throw new PasswordErrorException("La contraseña no cumple con los requisitos de seguridad.");
        }
        if (!Arrays.asList(Role.values()).contains(usuario.getRole())) {
            throw new RolInvalidoErrorException("Rol no válido: " + usuario.getRole().name());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword().getValue()));
        return usuarioRepository.save(usuario);
    }
}
