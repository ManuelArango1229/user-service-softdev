package com.devsoft.user_service.domain.exceptions;

/**
 * Excepción que se lanza cuando el usuario ya existe.
 */
public class UsuarioExisteErrorException extends RuntimeException {
    /**
     * Constructor de la excepción.
     * @param message mensaje de error
     */
    public UsuarioExisteErrorException(final String message) {
        super(message);
    }
}
