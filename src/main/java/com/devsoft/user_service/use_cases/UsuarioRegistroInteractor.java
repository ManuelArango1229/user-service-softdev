package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.repositories.AdministradorRepositoryPort;
import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import com.devsoft.user_service.domain.value_objects.Password;
import com.devsoft.user_service.use_cases.dtos.UsuarioRegisterDto;

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
     * respositorio de administradores.
     */
    private final AdministradorRepositoryPort administradorRepository;
    /**
     * servicio de encriptación de contraseñas.
     */
    private final PasswordEncoderPort passwordEncoder;

    /**
     * Constructor de la clase UsuarioRegistroInteractor.
     *
     * @param usuario
     * @return Usuario usuario registrado
     */
    public Usuario execute(final UsuarioRegisterDto usuario) {
        Usuario user = usuarioRepository.findByDni(usuario.getDni()).orElse(null);
        if (user != null && user.getDni() != null) {
            throw new UsuarioExisteErrorException("El usuario con DNI " + usuario.getDni() + " ya existe.");
        }
        if (!usuario.getPassword().matches(Password.PASSWORD_PATTERN)) {
            throw new PasswordErrorException("La contraseña no cumple con los requisitos de seguridad.");
        }
        Usuario nuevoUsuario;
        switch (usuario.getRole()) {
            case "CLIENTE":
                nuevoUsuario = new Cliente(usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getEdad(),
                        usuario.getAddress(), usuario.getGenero(), usuario.getPhoneNumber());
                break;
            case "ADMINISTRADOR":
                nuevoUsuario = new Administrador(usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword());
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                return administradorRepository.save(nuevoUsuario);
            case "REPARTIDOR":
                nuevoUsuario = new Repartidor(usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getVehiculoAsignado());
                break;
            default:
                throw new RolInvalidoErrorException("Rol no válido: " + usuario.getRole());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(nuevoUsuario);
    }
}
