package com.devsoft.user_service.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de seguridad.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Método para configurar la seguridad de la aplicación.
     * @param http objeto HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception excepción
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/h2-console/**").permitAll() // Permite el acceso a H2 Console
                .requestMatchers("/auth/**").permitAll() // Permite el acceso a la ruta de autenticación
                .anyRequest().authenticated() // El resto de las rutas requieren autenticación
            )
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Desactiva CSRF para H2 Console
                .ignoringRequestMatchers("/auth/**") // Desactiva CSRF para la ruta de autenticación
            )
            .headers((headers) -> headers
                .frameOptions((frameOptions) -> frameOptions.disable()) // Permite frames
            );
        return http.build();
    }
}
