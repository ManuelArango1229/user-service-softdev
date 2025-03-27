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
        // Validación de campos obligatorios
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        if (usuario.getDni() == null || usuario.getDni().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser null o vacío.");
        }
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser null o vacío.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser null o vacío.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser null o vacía.");
        }
        if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede ser null o vacío.");
        }
        Usuario user;
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
                return clienteRepository.save(new Cliente(
                        usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getEdad(),
                        usuario.getAddress(), usuario.getGenero(), usuario.getPhoneNumber()));
            case "ADMINISTRADOR":
                return administradorRepository.save(new Administrador(
                        usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword()));
            case "REPARTIDOR":
                if (usuario.getVehiculoAsignado() == null) {
                    throw new IllegalArgumentException("El vehículo asignado no puede ser null para un repartidor.");
                }
                return repartidorRepository.save(new Repartidor(
                        usuario.getDni(), usuario.getNombre(), usuario.getEmail(),
                        usuario.getPassword(), usuario.getVehiculoAsignado()));
            default:
                throw new RolInvalidoErrorException("Rol no válido: " + usuario.getRole());
        }
    }
}
