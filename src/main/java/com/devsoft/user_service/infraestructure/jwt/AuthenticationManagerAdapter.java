package com.devsoft.user_service.infraestructure.jwt;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.devsoft.user_service.domain.services.AuthenticationManagerPort;


/**
 * Adaptador que implementa la interfaz {@link AuthenticationManagerPort}
 * y delega la autenticación a un {@link AuthenticationManager} de Spring Security.
 */
@Component
public class AuthenticationManagerAdapter implements AuthenticationManagerPort {

    /** El gestor de autenticación de Spring Security. */
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor que inicializa el adaptador con un {@link AuthenticationManager}.
     *
     * @param authenticationManagerParam El gestor de autenticación de Spring Security.
     */
    public AuthenticationManagerAdapter(final AuthenticationManager authenticationManagerParam) {
        this.authenticationManager = authenticationManagerParam;
    }

    /**
     * Autentica a un usuario con las credenciales proporcionadas.
     *
     * @param username El nombre de usuario del usuario que intenta autenticarse.
     * @param password La contraseña del usuario.
     * @return Un objeto {@link Authentication} con los detalles de la autenticación.
     * @throws AuthenticationException Si la autenticación falla.
     */
    @Override
    public Object authenticate(final String username, final String password) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authentication);
    }
}
