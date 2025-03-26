package com.devsoft.user_service.domain.services;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtServicioPort {
    /**
     * Genera un token JWT para un usuario.
     *
     * @param usuario Identificador del usuario autenticado.
     * @param role    Rol del usuario autenticado.
     * @return Token JWT generado.
     */
    String obtenerToken(String usuario, String role);

    /**
     * Genera un token JWT con información adicional (claims).
     *
     * @param claims Datos adicionales a incluir en el token.
     * @param usuario Identificador del usuario autenticado.
     * @param role    Rol del usuario autenticado.
     * @return Token JWT generado.
     */
    String obtenerToken(Map<String, Object> claims, String usuario, String role);

    /**
     * Obtiene todos los claims (datos) contenidos en un token JWT.
     *
     * @param token Token JWT.
     * @return Claims extraídos del token.
     */
    Map<String, Object> obtenerTodosLosClaims(String token);

    /**
     * Valida si un token es válido para un usuario dado.
     *
     * @param token Token JWT.
     * @param usuario Identificador del usuario autenticado.
     * @return true si el token es válido, false si no lo es.
     */
    boolean validarToken(String token, String usuario);

    /**
     * Extrae el identificador del usuario desde un token JWT.
     *
     * @param token Token JWT.
     * @return Identificador del usuario contenido en el token.
     */
    String obtenerUsernameDesdeToken(String token);

    /**
     * Verifica si un token ha expirado.
     *
     * @param token Token JWT.
     * @return true si el token ha expirado, false si aún es válido.
     */
    boolean validarTokenVencido(String token);

    /**
     * Obtiene un dato específico desde un token JWT.
     *
     * @param token Token JWT.
     * @param claimsResolver Función para resolver el dato requerido.
     * @param <T> Tipo del dato a extraer.
     * @return Valor del dato solicitado.
     */
    <T> T obtenerClaim(String token, Function<Map<String, Object>, T> claimsResolver);

    /**
     * Obtiene la fecha de expiración de un token JWT.
     *
     * @param token Token JWT.
     * @return Fecha de expiración del token.
     */
    Date obtenerVencimiento(String token);
}
