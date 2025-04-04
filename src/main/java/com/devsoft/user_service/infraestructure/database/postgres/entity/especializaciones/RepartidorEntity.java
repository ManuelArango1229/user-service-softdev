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
 * El método de entrega asignado al repartidor.
 * Este campo determina el tipo de método de entrega que el repartidor utilizará
 * para realizar sus entregas.
 */
@Entity
@Table(name = "repartidores")
@PrimaryKeyJoinColumn(name = "dni")
@Getter
@Setter
@NoArgsConstructor
@Profile("postgres")
public class RepartidorEntity extends UsuarioEntity {
    /** Representa el método de entrega asignado al repartidor. */
    private String metodoAsignado;

    /**
     * Constructor para crear una nueva instancia de RepartidorEntity.
     *
     * @param dni                 El DNI del repartidor que servirá como
     *                            identificador
     *                            único
     * @param nombre              El nombre completo del repartidor
     * @param email               El correo electrónico del repartidor
     * @param password            La contraseña del repartidor (debe estar
     *                            encriptada
     *                            antes de llegar aquí)
     * @param metodoAsignadoParam El método de entrega asignado al repartidor
     */
    public RepartidorEntity(final String dni, final String nombre, final String email, final String password,
            final String metodoAsignadoParam) {
        super(dni, nombre, email, password, Role.REPARTIDOR);
        this.metodoAsignado = metodoAsignadoParam;
    }
}
