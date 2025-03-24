package com.devsoft.user_service.use_cases.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Objeto de Transferencia de Datos (DTO) para actualizar la información del
 * usuario.
 * Esta clase se utiliza para encapsular los datos necesarios para actualizar
 * los detalles de un usuario.
 * Incluye el nombre, el correo electrónico y la contraseña del usuario.
 *
 * <p>
 * Ejemplo de uso:
 * </p>
 *
 * <pre>
 * {@code
 * UsuarioUpdateDto userUpdate = new UsuarioUpdateDto("Juan Pérez", "juan.perez@ejemplo.com", "contraseñaSegura123");
 * String nombre = userUpdate.getNombre();
 * String email = userUpdate.getEmail();
 * String password = userUpdate.getPassword();
 * }
 * </pre>
 */
@AllArgsConstructor
@Getter
public class UsuarioUpdateDto {
    /**
     * El nombre del usuario.
     */
    private String nombre;
    /**
     * El correo electrónico del usuario.
     */
    private String email;
    /**
     * La contraseña del usuario.
     */
    private String password;
}
