package com.devsoft.user_service.infraestructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio para la gestión de tokens JWT.
 */
@Service
public class JwtServicio {

    /**
     * Clave secreta utilizada para la firma del token.
     */
    private static final String SECRET = "secret";

    /**
     * Tiempo de vencimiento del token (30 min).
     */
    private static final Long EXPIRATION = 1800000L;

    /**
     * Genera un token JWT para un usuario.
     *
     * @param usuario Detalles del usuario autenticado.
     * @return Token JWT generado.
     */
    public String obtenerToken(final UserDetails usuario) {
        return obtenerToken(new HashMap<>(), usuario);
    }

    /**
     * Genera un token JWT con información adicional (claims).
     *
     * @param claims Datos adicionales a incluir en el token.
     * @param usuario Detalles del usuario autenticado.
     * @return Token JWT generado.
     */
    private String obtenerToken(final Map<String, Object> claims,
                                final UserDetails usuario) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(obtenerLlave())
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar los tokens.
     *
     * @return Llave de firma JWT.
     */
    private Key obtenerLlave() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Valida si un token es válido para un usuario dado.
     *
     * @param token Token JWT.
     * @param userDetails Detalles del usuario autenticado.
     * @return true si el token es válido, false si no lo es.
     */
    boolean validarToken(final String token, final UserDetails userDetails) {
        final String username = obtenerUsernameDesdeToken(token);
        return (username.equals(userDetails.getUsername())
                && !validarTokenVencido(token));
    }

    /**
     * Extrae el nombre de usuario desde un token JWT.
     *
     * @param token Token JWT.
     * @return Nombre de usuario contenido en el token.
     */
    String obtenerUsernameDesdeToken(final String token) {
        return obtenerClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token ha expirado.
     *
     * @param token Token JWT.
     * @return true si el token ha expirado, false si aún es válido.
     */
    private boolean validarTokenVencido(final String token) {
        return obtenerVencimiento(token).before(new Date());
    }

    /**
     * Obtiene todos los claims (datos) contenidos en un token JWT.
     *
     * @param token Token JWT.
     * @return Claims extraídos del token.
     */
    public Claims obtenerTodosLosClaims(final String token) {
        SecretKey key = (SecretKey) obtenerLlave();
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return claimsJws.getPayload();
    }

    /**
     * Obtiene un claim específico desde un token JWT.
     *
     * @param token Token JWT.
     * @param claimsResolver Función para resolver el claim requerido.
     * @param <T> Tipo del claim a extraer.
     * @return Valor del claim solicitado.
     */
    public <T> T obtenerClaim(final String token,
                              final Function<Claims, T> claimsResolver) {
        final Claims claims = obtenerTodosLosClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de expiración de un token JWT.
     *
     * @param token Token JWT.
     * @return Fecha de expiración del token.
     */
    private Date obtenerVencimiento(final String token) {
        return obtenerClaim(token, Claims::getExpiration);
    }

}
