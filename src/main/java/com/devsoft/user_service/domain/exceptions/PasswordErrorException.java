package com.devsoft.user_service.domain.exceptions;

/**
 * Excepción que se lanza cuando la contraseña no es correcta.
 */
public class PasswordErrorException extends RuntimeException {
    /**
     * Constructor de la excepción.
     * @param message mensaje de error
     */
    public PasswordErrorException(final String message) {
        super(message);
    }
}
