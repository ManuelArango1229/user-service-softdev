package com.devsoft.user_service.domain.entities;

import com.devsoft.user_service.domain.value_objects.Email;
import com.devsoft.user_service.domain.value_objects.Password;
import com.devsoft.user_service.domain.value_objects.Role;

/**
 * La clase Usuario representa a un usuario en el sistema.
 * Contiene información básica como DNI, nombre, email, contraseña y rol.
 */
public final class Usuario {
    /**
     * Identificador único del usuario.
     */
    private final String dni;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Correo electrónico del usuario.
     */
    private Email email;
    /**
     * Contraseña del usuario.
     */
    private Password password;
    /**
     * Rol del usuario.
     */
    private Role role;

    /**
     * Constructor de la clase Usuario.
     *
     * @param dniParam      Identificación única del usuario.
     * @param nombreParam   Nombre del usuario.
     * @param emailParam    Correo electrónico del usuario.
     * @param passwordParam Contraseña del usuario.
     * @param roleParam     Rol del usuario.
     */
    public Usuario(final String dniParam, final String nombreParam,
            final String emailParam, final String passwordParam,
            final String roleParam) {
        this.dni = dniParam;
        this.nombre = nombreParam;
        this.email = new Email(emailParam);
        this.password = new Password(passwordParam);
        this.role = Role.valueOf(roleParam);
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return DNI del usuario.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public Password getPassword() {
        return password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return Rol del usuario.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param passwordParam Nueva contraseña del usuario.
     */
    public void setPassword(final String passwordParam) {
        password.setPassword(passwordParam);
    }

    /**
     * Establece el rol del usuario.
     *
     * @param roleParam Nuevo rol del usuario.
     */
    public void setRole(final String roleParam) {
        this.role = Role.valueOf(roleParam);
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param emailParam Nuevo correo electrónico del usuario.
     */
    public void setEmail(final Email emailParam) {
        this.email = emailParam;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombreParam Nuevo nombre del usuario.
     */
    public void setNombre(final String nombreParam) {
        this.nombre = nombreParam;
    }

    /**
     * Establece el DNI del usuario.
     *
     * @param dniParam Nuevo DNI del usuario.
     */
    public void setDni(final String dniParam) {
        this.dni = dniParam;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param roleParam Nuevo rol del usuario.
     */
    public void setRole(final Role roleParam) {
        this.role = roleParam;
    }
}
