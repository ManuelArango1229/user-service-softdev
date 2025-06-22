package com.devsoft.user_service.use_cases.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un repartidor.
 *
 * <p>
 * Esta excepción se utiliza para indicar que un repartidor específico no ha sido
 * encontrado en el sistema.
 * </p>
 *
 * <p>
 * Hereda de {@link RuntimeException}, lo que permite que sea lanzada sin necesidad
 * de ser declarada en la firma del método.
 * </p>
 */
public class RepartidorNoEncontradoException extends RuntimeException {
    /**
     * Constructor de la excepción.
     *
     * @param message Mensaje de error que describe la excepción.
     */
    public RepartidorNoEncontradoException(final String message) {
        super(message);
    }
}
