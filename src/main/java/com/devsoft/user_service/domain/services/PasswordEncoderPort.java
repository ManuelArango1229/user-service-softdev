package com.devsoft.user_service.domain.services;

/**
 * Interfaz que define los métodos para encriptar y comparar contraseñas.
 */
public interface PasswordEncoderPort {
    /**
     * Método para encriptar la contraseña.
     * @param password contraseña a encriptar
     * @return contraseña encriptada
     */
    String encode(String password);
    /**
     * Método para comparar la contraseña encriptada con la contraseña ingresada.
     * @param password contraseña ingresada
     * @param encodedPassword contraseña encriptada
     * @return true si la contraseña ingresada es igual a la contraseña encriptada
     */
    boolean matches(String password, String encodedPassword);
}
