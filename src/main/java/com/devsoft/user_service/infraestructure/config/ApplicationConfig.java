package com.devsoft.user_service.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devsoft.user_service.infraestructure.database.encoder.adapter.PasswordEncoderAdapter;
import com.devsoft.user_service.infraestructure.database.postgres.adapter.UsuarioRepositoryAdapter;
import com.devsoft.user_service.infraestructure.database.postgres.mapper.UsuarioEntityMapper;

import lombok.RequiredArgsConstructor;

/**
 * Configuración de la autenticación en la aplicación.
 *
 * Define y expone un {@link AuthenticationProvider} como un bean de Spring
 * Security.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Configura y proporciona un {@link AuthenticationManager} basado en
     * {@link AuthenticationConfiguration}.
     *
     * @param config La configuración de autenticación.
     * @return Un {@link AuthenticationManager} que utiliza
     *         {@link AuthenticationConfiguration}
     *         para la configuración de autenticación.
     * @throws Exception Si ocurre un error en la configuración de autenticación.
     */
    @Bean
    AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Adaptador para el repositorio de usuarios.
     */
    private final UsuarioRepositoryAdapter userRepository;

    /**
     * Adaptador para la codificación de contraseñas.
     */
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    /**
     * Configura y proporciona un {@link AuthenticationProvider} basado en
     * {@link DaoAuthenticationProvider}.
     *
     * @return Un {@link AuthenticationProvider} que utiliza UserDetailService
     *         para la carga de detalles del usuario y
     *         {@link PasswordEncoderAdapter} para la codificación de contraseñas.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoderAdapter.getEncoder());
        return authenticationProvider;
    }

    /**
     * Configura y proporciona un {@link UserDetailsService} basado en
     * {@link UserDetailsService}.
     *
     * @return Un {@link UserDetailsService} que utiliza
     *         {@link UsuarioRepositoryAdapter}
     *         para la carga de detalles del usuario.
     */
    @Primary
    @Bean
    UserDetailsService userDetailService() {
        return username -> UsuarioEntityMapper.toUsuarioEntity(userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

}
