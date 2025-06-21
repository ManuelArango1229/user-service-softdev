package com.devsoft.user_service.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RepartidorResponseDto es un objeto de transferencia de datos (DTO) que
 * representa la información de un repartidor que se devuelve en las respuestas
 * de la API.
 */
@AllArgsConstructor
@Data
public class RepartidorResponseDto {
    /**
     * El Documento Nacional de Identidad (DNI) del repartidor.
     */
    private String dni;
    /**
     * El nombre del repartidor.
     */
    private String nombre;
    /**
     * El correo electrónico del repartidor.
     */
    private String email;
    /**
     * El rol del repartidor en el sistema.
     */
    private String role;
    /**
     * El token JWT generado para el repartidor.
     */
    private String metodoAsignado;

    /**
     * Constructor por defecto.
     */
    public RepartidorResponseDto() {
    }
}
