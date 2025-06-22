package com.devsoft.user_service.infraestructure.database.postgres.entity.especializaciones;

import org.springframework.context.annotation.Profile;

import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.postgres.entity.UsuarioEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa a un administrador en la base de datos H2.
 * Extiende de {@link UsuarioEntity} heredando sus atributos base y agregando
 * la especialización necesaria para el rol de Administrador.
 *
 * @see UsuarioEntity
 * @see Role
 */
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "dni")
@NoArgsConstructor
@Profile("postgres")
public class AdministradorEntity extends UsuarioEntity {
    /**
     * Constructor para crear una nueva instancia de AdministradorEntity.
     *
     * @param dni      El DNI del administrador que servirá como identificador único
     * @param nombre   El nombre completo del administrador
     * @param email    El correo electrónico del administrador
     * @param password La contraseña del administrador (debe estar encriptada antes
     *                 de
     *                 llegar aquí)
     */
    public AdministradorEntity(final String dni, final String nombre, final String email, final String password) {
        super(dni, nombre, email, password, Role.ADMINISTRADOR);
    }
}
