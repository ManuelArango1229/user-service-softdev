package com.devsoft.user_service.use_cases;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.devsoft.user_service.domain.entities.Usuario;
import com.devsoft.user_service.domain.repositories.UsuarioRepositoryPort;
import com.devsoft.user_service.domain.services.JwtServicioPort;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;
import com.devsoft.user_service.infraestructure.database.h2.mapper.UsuarioEntityMapper;
import com.devsoft.user_service.infraestructure.rest.dto.UsuarioResponseDto;

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
    private final AuthenticationManager authenticationManager;

    /**
     * Autentica a un usuario en el sistema y genera un token JWT para su sesión.
     *
     * @param request los datos del usuario que intenta autenticarse.
     * @return un {@link UsuarioResponseDto} con la información del usuario y el token JWT generado.
     * @throws AuthenticationException si la autenticación falla.
     * @throws NoSuchElementException si el usuario no es encontrado en el repositorio.
     */
    public UsuarioResponseDto login(final Usuario request) {
        // Autentica al usuario con el correo y la contraseña proporcionados
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Busca el usuario en el repositorio y lo convierte a entidad
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail().getValue())
                                           .orElseThrow();
        UsuarioEntity usuarioEntity = UsuarioEntityMapper.toUsuarioEntity(usuario);

        // Convierte la entidad en un objeto UserDetails para generar el token
        UserDetails user = usuarioEntity;

        // Genera el token JWT para el usuario autenticado
        String token = jwtServicio.obtenerToken(user.getUsername());

        // Retorna la respuesta con los datos del usuario y el token generado
        return new UsuarioResponseDto(
            usuario.getDni(),
            usuario.getNombre(),
            usuario.getEmail().getValue(),
            usuario.getRole().name(),
            token
        );
    }
}
