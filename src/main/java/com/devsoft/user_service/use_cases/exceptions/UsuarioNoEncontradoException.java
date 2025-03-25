package com.devsoft.user_service.use_cases.exceptions;

/**
 * Excepción lanzada cuando un usuario no es encontrado en el repositorio.
 */
public class UsuarioNoEncontradoException extends RuntimeException {
    /**
     * @param message mensaje de error
     */
    public UsuarioNoEncontradoException(final String message) {
        super(message);
    }
}
