package com.devsoft.user_service.use_cases;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.AuthenticationManagerPort;
import com.devsoft.user_service.domain.services.JwtServicioPort;
import com.devsoft.user_service.domain.services.UserDetailsPort;

import lombok.RequiredArgsConstructor;


/**
 * Clase encargada de manejar la autenticación de usuarios y la generación de tokens JWT.
 */
@Component
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
     * @param request los datos del usuario que intenta autenticarse.
     * @return un {@link Map} con la información del usuario y el token JWT generado.
     * @throws Exception si hay un error en la autenticación.
     */
    public Map<Usuario, String> login(final Usuario request) throws Exception {
        // Autentica al usuario con el correo y la contraseña proporcionados
        authenticationManager.authenticate(request.getEmail().getValue(), request.getPassword().getValue());

        // Busca el usuario en el repositorio
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail().getValue())
                                           .orElseThrow();

        // Obtiene detalles del usuario usando una abstracción en la capa de dominio
        String username = userDetailsService.getUsername(usuario);

        // Genera el token JWT con el username
        String token = jwtServicio.obtenerToken(username);

        // Retorna la respuesta con los datos del usuario y el token generado
        return new HashMap<Usuario, String>() {{
            put(usuario, token);
        }};
    }
}
