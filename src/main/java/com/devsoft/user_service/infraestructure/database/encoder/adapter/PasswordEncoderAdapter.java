package com.devsoft.user_service.infraestructure.database.encoder.adapter;

import com.devsoft.user_service.domain.services.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa la interfaz PasswordEncoderPort.
 */
@RequiredArgsConstructor
@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {
    /**
     * Instancia de BCryptPasswordEncoder.
     */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /**
     * Método para encriptar la contraseña.
     * @param password contraseña a encriptar
     * @return contraseña encriptada
     */
    @Override
    public String encode(final String password) {
        return  encoder.encode(password);
    }
    /**
     * Método para comparar la contraseña encriptada con la contraseña ingresada.
     * @param password contraseña ingresada
     * @param encodedPassword contraseña encriptada
     * @return true si la contraseña ingresada es igual a la contraseña encriptada
     */
    @Override
    public boolean matches(final String password, final String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }

    /**
     * Método que devuelve la instancia de BCryptPasswordEncoder.
     * @return instancia de BCryptPasswordEncoder
     */
    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }
}
