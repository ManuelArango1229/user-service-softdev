package com.devsoft.user_service.domain.entities.especializaciones;

import com.devsoft.user_service.domain.entities.Usuario;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una entidad Repartidor que extiende de la entidad base Usuario.
 * Esta clase incluye atributos adicionales específicos de un repartidor.
 */
@Getter
@Setter
public class Repartidor extends Usuario {
    /**
     * Vehículo asignado al repartidor.
     */
    private String vehiculoAsignado;

    /**
     * Construye una nueva instancia de Repartidor con los detalles especificados.
     *
     * @param dniParam              el identificador único del repartidor
     * @param nombreParam           el nombre del repartidor
     * @param emailParam            el correo electrónico del repartidor
     * @param passwordParam         la contraseña del repartidor
     * @param vehiculoAsignadoParam vehiculo Asignado el método de entrega asignado
     *                              al repartidor
     */
    public Repartidor(final String dniParam, final String nombreParam, final String emailParam,
            final String passwordParam, final String vehiculoAsignadoParam) {
        super(dniParam, nombreParam, emailParam, passwordParam, "REPARTIDOR");
        this.vehiculoAsignado = vehiculoAsignadoParam;
    }
}
