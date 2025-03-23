package com.devsoft.user_service.infraestructure.exception;

import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Manejador de excepciones para la excepción RolInvalidoErrorException.
     * @param e excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(RolInvalidoErrorException.class)
    public ResponseEntity<Map<String, String>> handleRolInvalido(final RolInvalidoErrorException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    /**
     * Manejador de excepciones para la excepción PasswordErrorException.
     * @param e excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(PasswordErrorException.class)
    public ResponseEntity<Map<String, String>> handlePasswordError(final PasswordErrorException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    /**
     * Manejador de excepciones para la excepción UsuarioExisteErrorException.
     * @param e excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(UsuarioExisteErrorException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioExiste(final UsuarioExisteErrorException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
    }
    /**
     * Construye una respuesta de error.
     * @param message mensaje de error
     * @param status estado de la respuesta
     * @return respuesta de error
     */
    private ResponseEntity<Map<String, String>> buildErrorResponse(final String message, final  HttpStatus status) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return new ResponseEntity<>(errorResponse, status);
    }
}
