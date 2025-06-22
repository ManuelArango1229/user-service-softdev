package com.devsoft.user_service.infraestructure.database.postgres.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.devsoft.user_service.domain.value_objects.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;

/**
 * Esta clase representa la entidad de usuario en la base de datos.
 */
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@Profile("postgres")
public class UsuarioEntity implements UserDetails {
    /**
     * Atributo que representa el DNI del usuario.
     */
    @Id
    private String dni;
    /**
     * Atributo que representa el nombre del usuario.
     */
    private String nombre;
    /**
     * Atributo que representa el correo electrónico del usuario.
     */
    @Column(nullable = false)
    private String email;
    /**
     * Atributo que representa la contraseña del usuario.
     */
    private String password;
    /**
     * Atributo que representa el rol del usuario.
     */
    @Enumerated(EnumType.STRING)
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
     * Obtiene las autoridades del usuario.
     *
     * @return Las autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el nombre de usuario del usuario, en este caso el correo electrónico.
     *
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Constructor vacío.
     */
    public UsuarioEntity() {
    }

    /**
     * Retorna el rol del usuario.
     *
     * @return el rol del usuario.
     */
    public Role getRol() {
        return this.rol;
    }
}
