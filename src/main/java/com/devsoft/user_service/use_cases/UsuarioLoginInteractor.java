package com.devsoft.user_service.use_cases;

import org.springframework.stereotype.Service;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.AuthenticationManagerPort;
import com.devsoft.user_service.domain.services.JwtServicioPort;
import com.devsoft.user_service.domain.services.UserDetailsPort;

import lombok.RequiredArgsConstructor;


/**
 * Clase encargada de manejar la autenticación de usuarios y la generación de tokens JWT.
 */
@Service
@RequiredArgsConstructor
public class UsuarioLoginInteractor {

    /** Repositorio de usuarios para la consulta de datos. */
    private final UsuarioRepositoryPort usuarioRepository;

    /** Servicio para la generación y validación de tokens JWT. */
    private final JwtServicioPort jwtServicio;

    /** Manejador de autenticación de Spring Security. */
    private final AuthenticationManagerPort authenticationManager;

    /** Servicio para obtener detalles del usuario (sin depender de Spring Security). */
    private final UserDetailsPort userDetailsService;

    /**
     * Autentica a un usuario en el sistema y genera un token JWT para su sesión.
     *
     * @param email   el correo electronico del usuario.
     * @param password la contraseña del usuario.
     * @return el token JWT generado.
     * @throws Exception si hay un error en la autenticación.
     */
    public String login(final String email, final String password) throws Exception {
        // Autentica al usuario con el correo y la contraseña proporcionados
        authenticationManager.authenticate(email, password);

        // Busca el usuario en el repositorio
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        // Obtiene detalles del usuario usando una abstracción en la capa de dominio
        String username = userDetailsService.getUsername(usuario);

        // Genera el token JWT con el username
        String token = jwtServicio.obtenerToken(username);

        // Retorna la respuesta con el token generado
        return token;
    }
}
