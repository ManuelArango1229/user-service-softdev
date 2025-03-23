package com.devsoft.user_service.domain.value_objects;

import java.util.regex.Pattern;

/**
 * Clase que representa una contraseña segura.
 */
public class Password {

    /**
     * Expresión regular para validar una contraseña segura.
     * Debe contener al menos una minúscula, una mayúscula, un número y tener mínimo 8 caracteres.
     */
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    /**
     * Patrón compilado para validar contraseñas.
     */
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Valor de la contraseña.
     */
    private String value;

    /**
     * Constructor de la clase Password.
     * @param passwordValue Valor de la contraseña.
     * @throws IllegalArgumentException Si la contraseña es nula, vacía o no cumple con el formato.
     */
    public Password(final String passwordValue) {
        if (passwordValue == null || passwordValue.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }
        if (!PATTERN.matcher(passwordValue).matches()) {
            throw new IllegalArgumentException("La contraseña no tiene un formato válido");
        }
        this.value = passwordValue;
    }

    /**
     * Método que devuelve el valor de la contraseña.
     * @return Valor de la contraseña.
     */
    public String getValue() {
        return value;
    }

    /**
     * Representación en cadena de la contraseña (oculta por seguridad).
     * @return Cadena de asteriscos en lugar de la contraseña real.
     */
    @Override
    public String toString() {
        return "********";
    }

    /**
     * Método que establece el valor de la contraseña.
     * @param newPassword Nueva contraseña a establecer.
     */
    public void setPassword(final String newPassword) {
        this.value = newPassword;
    }
}
