package com.devsoft.user_service.domain.value_objects;

import java.util.Arrays;

/**
 * Enumeración que representa los roles de los usuarios.
 */
public enum Role {
    /**
     * Representa el rol de un cliente.
     */
    CLIENTE,
    /**
     * Representa el rol de un administrador.
     */
    ADMINISTRADOR,
    /**
     * Representa el rol de un repartidor.
     */
    REPARTIDOR;
    /**
     * Convierte un String en un Role, asegurando que sea válido.
     *
     * @param roleStr El nombre del rol en String.
     * @return El Role correspondiente.
     * @throws IllegalArgumentException Si el rol no es válido.
     */
    public static Role fromString(final String roleStr) {
        if (roleStr == null || roleStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío. Los roles permitidos son: " + Arrays.toString(values()));
        }

        return Arrays.stream(values())
                .filter(role -> role.name().equalsIgnoreCase(roleStr.trim())) // Elimina espacios y compara sin importar mayúsculas
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Rol inválido: '" + roleStr + "'. Los roles permitidos son: " + Arrays.toString(values())));
    }
}
