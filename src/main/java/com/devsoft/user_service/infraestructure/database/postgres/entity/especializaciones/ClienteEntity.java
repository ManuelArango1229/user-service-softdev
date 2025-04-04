package com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones;

import org.springframework.context.annotation.Profile;

import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa a un Cliente en la base de datos.
 * Extiende de UsuarioEntity y añade atributos específicos de un cliente.
 * Esta entidad se mapea a la tabla 'clientes' en la base de datos H2.
 */
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "dni")
@Getter
@Setter
@NoArgsConstructor
@Profile("postgres")
public class ClienteEntity extends UsuarioEntity {
    /**
     * Edad del cliente.
     */
    private int edad;
    /**
     * Dirección de residencia del cliente.
     */
    private String direccion;
    /**
     * Género del cliente.
     */
    private String genero;
    /**
     * Número de teléfono del cliente.
     */
    private String telefono;

    /**
     * Constructor para crear una nueva instancia de ClienteEntity.
     *
     * @param dni            DNI del cliente que sirve como identificador único
     * @param nombre         Nombre completo del cliente
     * @param email          Correo electrónico del cliente
     * @param password       Contraseña del cliente
     * @param edadParam      Edad del cliente
     * @param direccionParam Dirección de residencia del cliente
     * @param generoParam    Género del cliente
     * @param telefonoParam  Número de teléfono del cliente
     */

    public ClienteEntity(final String dni, final String nombre, final String email, final String password,
            final int edadParam, final String direccionParam,
            final String generoParam, final String telefonoParam) {
        super(dni, nombre, email, password, Role.CLIENTE);
        this.edad = edadParam;
        this.direccion = direccionParam;
        this.genero = generoParam;
        this.telefono = telefonoParam;
    }
}
