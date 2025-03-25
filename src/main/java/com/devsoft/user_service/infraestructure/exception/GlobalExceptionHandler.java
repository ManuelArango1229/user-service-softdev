package com.devsoft.user_service.infraestructure.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.devsoft.user_service.domain.exceptions.PasswordErrorException;
import com.devsoft.user_service.domain.exceptions.RolInvalidoErrorException;
import com.devsoft.user_service.domain.exceptions.UsuarioExisteErrorException;
import com.devsoft.user_service.use_cases.exceptions.UsuarioNoEncontradoException;

/**
 * Manejador global de excepciones para la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejador de excepciones para errores de validación de campos.
     *
     * @param ex Excepción lanzada.
     * @return ResponseEntity con los errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Manejador de excepciones para la excepción UsuarioExisteErrorException.
     *
     * @param ex excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(UsuarioExisteErrorException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioExiste(final UsuarioExisteErrorException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Manejador de excepciones para la excepción PasswordErrorException.
     *
     * @param ex excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(PasswordErrorException.class)
    public ResponseEntity<Map<String, String>> handlePasswordError(final PasswordErrorException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Manejador de excepciones para la excepción RolInvalidoErrorException.
     *
     * @param ex excepción lanzada
     * @return respuesta de error
     */
    @ExceptionHandler(RolInvalidoErrorException.class)
    public ResponseEntity<Map<String, String>> handleRolInvalido(final RolInvalidoErrorException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * @param ex
     * @return response con el error
     */
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNoExiste(final UsuarioNoEncontradoException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
