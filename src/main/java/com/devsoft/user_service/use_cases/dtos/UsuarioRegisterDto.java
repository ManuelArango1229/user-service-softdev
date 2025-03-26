package com.devsoft.user_service.use_cases.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) utilizado para el registro de nuevos usuarios en
 * el sistema.
 * Contiene toda la información necesaria para crear un nuevo usuario.
 */
@Getter
@Setter
@AllArgsConstructor
public class UsuarioRegisterDto {
    /** Documento Nacional de Identidad del usuario. */
    private String dni;
    /** Nombre completo del usuario. */
    private String nombre;
    /** Dirección de correo electrónico del usuario. */
    private String email;
    /** Contraseña del usuario. */
    private String password;
    /** Identificador del vehículo asignado al usuario (si aplica). */
    private String vehiculoAsignado;
    /** Rol del usuario en el sistema. */
    private String role;
    /** Edad del usuario. */
    private int edad;
    /** Dirección del usuario. */
    private String address;
    /** Género del usuario. */
    private String genero;
    /** Teléfono del usuario. */
    private String phoneNumber;
}
