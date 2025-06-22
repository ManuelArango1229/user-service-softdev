package com.devsoft.user_service.domain.exceptions;

/**
 * Excepción que se lanza cuando el rol no es válido.
 */
public class RolInvalidoErrorException extends RuntimeException {
    /**
     * Constructor de la excepción.
     * @param message mensaje de error
     */
    public RolInvalidoErrorException(final String message) {
        super(message);
    }
}
