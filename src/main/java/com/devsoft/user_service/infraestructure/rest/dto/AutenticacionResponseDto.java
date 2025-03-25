package com.devsoft.user_service.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AutenticacionResponseDto es un objeto de transferencia de datos (DTO) que
 * representa la información
 * de autenticación que se devuelve en las respuestas de la API.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacionResponseDto {

    /**
     * Token JWT generado durante la autenticación.
     *
     */
    private String token;
}
