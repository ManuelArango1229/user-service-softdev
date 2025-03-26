package com.devsoft.user_service.domain.entities.especializaciones;

import com.devsoft.user_service.domain.entities.Usuario;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una entidad Cliente que extiende de la entidad base Usuario.
 * Esta clase incluye atributos adicionales específicos de un cliente.
 */
@Getter
@Setter
public class Cliente extends Usuario {
    /**
     * Edad del cliente.
     */
    private int edad;
    /**
     * Dirección del cliente.
     */
    private String direccion;
    /**
     * Género del cliente.
     */
    private String genero;
    /**
     * Teléfono del cliente.
     */
    private String telefono;

    /**
     * Construye una nueva instancia de Cliente con los detalles especificados.
     *
     * @param dniParam       el identificador único del cliente
     * @param nombreParam    el nombre del cliente
     * @param emailParam     el correo electrónico del cliente
     * @param passwordParam  la contraseña del cliente
     * @param edadParam      la edad del cliente
     * @param direccionParam la dirección del cliente
     * @param generoParam    el género del cliente
     * @param telefonoParam  el teléfono del cliente
     */
    public Cliente(final String dniParam, final String nombreParam, final String emailParam, final String passwordParam,
            final int edadParam,
            final String direccionParam, final String generoParam,
            final String telefonoParam) {
        super(dniParam, nombreParam, emailParam, passwordParam, "CLIENTE");
        this.edad = edadParam;
        this.direccion = direccionParam;
        this.genero = generoParam;
        this.telefono = telefonoParam;
    }
}
