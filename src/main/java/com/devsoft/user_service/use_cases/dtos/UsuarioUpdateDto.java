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
    /** Nombre completo del usuario. */
    private String nombre;
    /** Dirección de correo electrónico del usuario. */
    private String email;
    /** Contraseña del usuario. */
    private String password;
    /** Identificador del vehículo asignado al usuario (si aplica). */
    private String vehiculoAsignado;
    /** Edad del usuario. */
    private int edad;
    /** Dirección del usuario. */
    private String address;
    /** Género del usuario. */
    private String genero;
    /** Teléfono del usuario. */
    private String phoneNumber;
}
