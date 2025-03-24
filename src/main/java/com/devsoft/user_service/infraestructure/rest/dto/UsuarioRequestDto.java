package com.devsoft.user_service.infraestructure.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UsuarioRequestDto es un objeto de transferencia de datos (DTO) que representa
 * la información
 * necesaria para registrar un usuario en el sistema.
 */
@AllArgsConstructor
@Data
public class UsuarioRequestDto {
    /**
     * Valores mínimo para el DNI.
     */
    public static final int MIN_DNI = 8;
    /**
     * Valor máximo para el DNI.
     */
    public static final int MAX_DNI = 10;
    /**
     * El Documento Nacional de Identidad (DNI) del usuario.
     */
    @NotBlank
    @Size(min = MIN_DNI, max = MAX_DNI, message = "El DNI debe tener entre 8 y 10 caracteres")
    private String dni;
    /**
     * El nombre del usuario.
     */
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    /**
     * El correo electrónico del usuario.
     */
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email no tiene un formato válido")
    private String email;
    /**
     * La contraseña del usuario.
     */
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = MIN_DNI, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe contener al menos una mayúscula y un número")
    private String password;
    /**
     * El rol del usuario en el sistema.
     */
    @NotBlank(message = "El rol no puede estar vacío")
    private String role;
}
