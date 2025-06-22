package com.devsoft.user_service.use_cases.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de un usuario.
 * Contiene información básica del usuario, como su DNI, nombre, correo electrónico,
 * contraseña, método asignado, rol, edad, dirección, género y número de teléfono.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {
    /**
     * DNI del usuario.
     */
    private String dni;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Correo electrónico del usuario.
     */
    private String email;
    /**
     * Método asignado al usuario.
     */
    private String metodoAsignado;
    /**
     * Rol del usuario (ej. CLIENTE, ADMINISTRADOR, REPARTIDOR).
     */
    private String role;
    /**
     * Edad del usuario.
     */
    private int edad;
    /**
     * Dirección del usuario.
     */
    private String direccion;
    /**
     * Género del usuario.
     */
    private String genero;
    /**
     * Número de teléfono del usuario.
     */
    private String telefono;
}
