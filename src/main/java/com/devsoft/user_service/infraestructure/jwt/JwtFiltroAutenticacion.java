package com.devsoft.user_service.infraestructure.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devsoft.user_service.infraestructure.jwt.adapter.JwtServicioAdapter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Filtro de autenticación JWT que se ejecuta una vez por solicitud.
 * Se encarga de validar el token JWT y autenticar al usuario si es válido.
 */
@Component
@RequiredArgsConstructor
public class JwtFiltroAutenticacion extends OncePerRequestFilter {

    /** Servicio para la gestión de tokens JWT. */
    private final JwtServicioAdapter jwtServicio;

    /** Servicio para la gestión de usuarios. */
    private final UserDetailsService userDetailsService;

    /** Prefijo estándar para los tokens JWT en los encabezados de autorización. */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Filtra cada solicitud HTTP para verificar la presencia y validez de un token
     * JWT.
     * Si el token es válido, autentica al usuario en el contexto de seguridad.
     *
     * @param request     la solicitud HTTP entrante.
     * @param response    la respuesta HTTP saliente.
     * @param filterChain la cadena de filtros de seguridad.
     * @throws ServletException en caso de errores del servlet.
     * @throws IOException      en caso de errores de entrada/salida.
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        final String token = obtenerTokenDeLaPeticion(request);
        final String username;

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtServicio.obtenerUsernameDesdeToken(token);
        String rol = jwtServicio.obtenerClaim(token, claims -> (String) claims.get("rol"));

        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtServicio.validarToken(token, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.singleton(
                                new SimpleGrantedAuthority("ROLE_" + rol)));

                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del encabezado de autorización de la solicitud HTTP.
     *
     * @param request la solicitud HTTP entrante.
     * @return el token JWT si está presente y tiene el formato correcto, o
     *         {@code null} si no está presente.
     */
    private String obtenerTokenDeLaPeticion(final HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final int cantidadCaracteres = 7;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(cantidadCaracteres);
        }

        return null;
    }
}
