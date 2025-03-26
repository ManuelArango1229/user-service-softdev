package com.devsoft.user_service.infraestructure.database.h2.entity.especializaciones;

import com.devsoft.user_service.domain.value_objects.Role;
import com.devsoft.user_service.infraestructure.database.h2.entity.UsuarioEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Entidad que representa a un Cliente en la base de datos H2.
 * Extiende de {@link UsuarioEntity} heredando sus atributos base y agregando
 * la especialización necesaria para el rol de Cliente.
 *
 * @see UsuarioEntity
 * @see Role
 */
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "dni")
public class AdministradorEntity extends UsuarioEntity {
    /**
     * Constructor para crear una nueva instancia de ClienteEntity.
     *
     * @param dni      El DNI del cliente que servirá como identificador único
     * @param nombre   El nombre completo del cliente
     * @param email    El correo electrónico del cliente
     * @param password La contraseña del cliente (debe estar encriptada antes de
     *                 llegar aquí)
     */
    public AdministradorEntity(String dni, String nombre, String email, String password) {
        super(dni, nombre, email, password, Role.ADMINISTRADOR);
    }
}
