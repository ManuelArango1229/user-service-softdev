package com.devsoft.user_service.domain.value_objects;

import java.util.regex.Pattern;

/**
 * Clase que representa un email.
 */
public class Email {

    /**
     * Expresión regular para validar el formato de un correo electrónico.
     */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    /**
     * Patrón compilado para validar emails.
     */
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Valor del correo electrónico.
     */
    private final String value;

    /**
     * Constructor de la clase.
     * @param valueParam Valor del email.
     * @throws IllegalArgumentException Si el email es nulo, vacío o no cumple el formato.
     */
    public Email(final String valueParam) {
        if (valueParam == null || valueParam.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        if (!PATTERN.matcher(valueParam).matches()) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
        this.value = valueParam;
    }

    /**
     * Método que devuelve el valor del email.
     * @return Valor del email.
     */
    public String getValue() {
        return value;
    }

    /**
     * Representación en cadena del objeto Email.
     * @return Email en formato de texto.
     */
    @Override
    public String toString() {
        return value;
    }
}
