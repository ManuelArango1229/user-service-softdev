package com.devsoft.user_service.infraestructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import com.devsoft.user_service.domain.services.JwtServicioPort;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio para la gestión de tokens JWT.
 * Se encarga de la generación, validación y extracción de información de los tokens.
 */
@Service
public class JwtServicioAdapter implements JwtServicioPort {

    /** Clave secreta utilizada para la firma de los tokens JWT. */
    private static final String SECRET = "secret";

    /** Tiempo de expiración del token en milisegundos (30 minutos). */
    private static final Long EXPIRATION = 1800000L;

    /**
     * Genera un token JWT para un usuario específico sin incluir información adicional.
     *
     * @param username el nombre de usuario para el cual se generará el token.
     * @return el token JWT generado.
     */
    @Override
    public String obtenerToken(final String username) {
        return obtenerToken(new HashMap<>(), username);
    }

    /**
     * Genera un token JWT con claims personalizados.
     *
     * @param claims   un mapa con los claims adicionales a incluir en el token.
     * @param username el nombre de usuario para el cual se generará el token.
     * @return el token JWT generado.
     */
    @Override
    public String obtenerToken(final Map<String, Object> claims, final String username) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(obtenerLlave())
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar y validar los tokens JWT.
     *
     * @return la clave secreta en formato {@link SecretKey}.
     */
    private SecretKey obtenerLlave() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Valida si un token JWT es válido comparando el nombre de usuario y verificando su expiración.
     *
     * @param token    el token JWT a validar.
     * @param username el nombre de usuario esperado en el token.
     * @return {@code true} si el token es válido, {@code false} en caso contrario.
     */
    @Override
    public boolean validarToken(final String token, final String username) {
        final String tokenUsername = obtenerUsernameDesdeToken(token);
        return (tokenUsername.equals(username) && !validarTokenVencido(token));
    }

    /**
     * Extrae el nombre de usuario desde el token JWT.
     *
     * @param token el token JWT.
     * @return el nombre de usuario contenido en el token.
     */
    @Override
    public String obtenerUsernameDesdeToken(final String token) {
        return obtenerClaim(token, claims -> (String) claims.get("sub"));
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token el token JWT a validar.
     * @return {@code true} si el token ha expirado, {@code false} en caso contrario.
     */
    @Override
    public boolean validarTokenVencido(final String token) {
        return obtenerVencimiento(token).before(new Date());
    }

    /**
     * Obtiene todos los claims del token JWT.
     *
     * @param token el token JWT.
     * @return un mapa con los claims contenidos en el token.
     */
    @Override
    public Map<String, Object> obtenerTodosLosClaims(final String token) {
        SecretKey key = obtenerLlave();
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return new HashMap<>(claimsJws.getPayload()); // Convertir Claims a Map<String, Object>
    }

    /**
     * Obtiene un claim específico del token JWT utilizando una función de resolución.
     *
     * @param token          el token JWT.
     * @param claimsResolver función para extraer un claim específico.
     * @param <T>            el tipo del claim extraído.
     * @return el valor del claim extraído.
     */
    @Override
    public <T> T obtenerClaim(final String token, final Function<Map<String, Object>, T> claimsResolver) {
        final Map<String, Object> claims = obtenerTodosLosClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de vencimiento del token JWT.
     *
     * @param token el token JWT.
     * @return la fecha de vencimiento del token.
     */
    @Override
    public Date obtenerVencimiento(final String token) {
        final int numero = 1000;
        return obtenerClaim(token, claims -> new Date((Long) claims.get("exp") * numero));
    }
}
