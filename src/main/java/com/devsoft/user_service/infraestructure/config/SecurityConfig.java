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
     * @param http objeto HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception excepción
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/h2-console/**", "/auth/**").permitAll() // Permite acceso sin autenticación
                .anyRequest().authenticated()// Requiere autenticación
        )
        .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si usas JWT
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sesiones
        .authenticationProvider(authProvider) // Configurar autenticación
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Filtro JWT
        return http.build();
    }

}
