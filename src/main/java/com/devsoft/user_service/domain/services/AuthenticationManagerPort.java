package com.devsoft.user_service.domain.services;

/**
 * Interfaz que define un contrato para la autenticación de usuarios.
 */
public interface AuthenticationManagerPort {

    /**
     * Autentica a un usuario con las credenciales proporcionadas.
     *
     * @param username El nombre de usuario del usuario que intenta autenticarse.
     * @param password La contraseña del usuario.
     * @return Un objeto que representa la sesión autenticada o detalles del usuario.
     * @throws Exception Si la autenticación falla debido a credenciales inválidas o un error en el proceso.
     */
    Object authenticate(String username, String password) throws Exception;
}
