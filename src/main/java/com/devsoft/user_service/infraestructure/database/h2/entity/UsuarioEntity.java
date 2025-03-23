package com.devsoft.user_service.infraestructure.database.h2.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.devsoft.user_service.domain.value_objects.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Esta clase representa la entidad de usuario en la base de datos.
 */
@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity implements UserDetails {
    /**
     * Atributo que representa el identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Atributo que representa el DNI del usuario.
     */
    private String dni;
    /**
     * Atributo que representa el nombre del usuario.
     */
    private String nombre;
    /**
     * Atributo que representa el correo electrónico del usuario.
     */
    private String email;
    /**
     * Atributo que representa la contraseña del usuario.
     */
    private String password;
    /**
     * Atributo que representa el rol del usuario.
     */
    private Role rol;

    /**
     * @param dniParam
     * @param nombreParam
     * @param emailParam
     * @param passwordParam
     * @param rolParam
     */
    public UsuarioEntity(final String dniParam,
            final String nombreParam,
            final String emailParam,
            final String passwordParam,
            final Role rolParam) {
        this.dni = dniParam;
        this.nombre = nombreParam;
        this.email = emailParam;
        this.password = passwordParam;
        this.rol = rolParam;
    }

    /**
     * Retorna la colección de permisos (autoridades) concedidos al usuario.
     *
     * @return una colección de objetos {@code GrantedAuthority} que representan
     *         los roles o permisos del usuario. En este caso, devuelve {@code null}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Obtiene el nombre de usuario del usuario autenticado.
     *
     * @return el correo electrónico del usuario, que se utiliza como nombre de usuario.
     */
    @Override
    public String getUsername() {
        return email;
    }
}
