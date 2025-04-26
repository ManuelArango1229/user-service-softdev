package com.devsoft.user_service.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.devsoft.user_service.infraestructure.jwt.JwtFiltroAutenticacion;

import lombok.RequiredArgsConstructor;

/**
 * Clase de configuración de seguridad.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Proveedor de autenticación.
     */
    private final AuthenticationProvider authProvider;

    /**
     * Filtro de autenticación JWT.
     */
    private final JwtFiltroAutenticacion jwtAuthenticationFilter;

    /**
     * Método para configurar la seguridad de la aplicación.
     *
     * @param http objeto HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception excepción
     */

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/auth/**", "/doc/**").permitAll()
                        .requestMatchers("/usuario/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/usuario/actualizar/**").hasRole("CLIENTE")
                        .requestMatchers("/usuario/eliminar/**").hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable())

                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
