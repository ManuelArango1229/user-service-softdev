package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.entities.especializaciones.Administrador;
import com.devsoft.user_service.domain.entities.especializaciones.Cliente;
import com.devsoft.user_service.domain.entities.especializaciones.Repartidor;
import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;
import com.devsoft.user_service.domain.repositories.AdministradorRepositoryPort;
import com.devsoft.user_service.domain.repositories.ClienteRepositoryPort;
import com.devsoft.user_service.domain.repositories.RepartidorRepositoryPort;
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
     * respositorio de Clientes.
     */
    private final ClienteRepositoryPort clienteRepository;

    /**
     * respositorio de administradores.
     */
    private final AdministradorRepositoryPort administradorRepository;

    /**
     * repositorio de repartidores.
     */
    private final RepartidorRepositoryPort repartidorRepository;
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
        Usuario user;
        System.out.println("Usuario: " + usuario.getRole());
        switch (usuario.getRole()) {
            case "CLIENTE":
                user = clienteRepository.findByDni(usuario.getDni()).orElse(null);
                break;
            case "ADMINISTRADOR":
                user = administradorRepository.findByDni(usuario.getDni()).orElse(null);
                break;
            case "REPARTIDOR":
                user = repartidorRepository.findByDni(usuario.getDni()).orElse(null);
                break;
            default:
                throw new RolInvalidoErrorException("Rol no válido: " + usuario.getRole());
        }
        if (user != null && user.getDni() != null) {
            throw new UsuarioExisteErrorException("El usuario con DNI " + usuario.getDni() + " ya existe.");
        }
        if (!usuario.getPassword().matches(Password.PASSWORD_PATTERN)) {
            throw new PasswordErrorException("La contraseña no cumple con los requisitos de seguridad.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        switch (usuario.getRole()) {
            case "CLIENTE":
                Cliente nuevoUsuario = new Cliente(usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getEdad(),
                        usuario.getAddress(), usuario.getGenero(), usuario.getPhoneNumber());
                return clienteRepository.save(nuevoUsuario);
            case "ADMINISTRADOR":
                Administrador nuevoUsuario2 = new Administrador(usuario.getDni(), usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getPassword());
                return administradorRepository.save(nuevoUsuario2);
            case "REPARTIDOR":
                Repartidor nuevoUsuario3 = new Repartidor(usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getVehiculoAsignado());
                return repartidorRepository.save(nuevoUsuario3);
            default:
                throw new RolInvalidoErrorException("Rol no válido: " + usuario.getRole());
        }

    }
}
