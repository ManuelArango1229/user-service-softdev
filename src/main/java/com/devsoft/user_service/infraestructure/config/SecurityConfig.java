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
            ).sessionManagement((sessionManagement) -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Desactiva la creación de sesiones
            )
            .authenticationProvider(authProvider) // Establece el proveedor de autenticación
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Agrega el filtro de autenticación JWT
        return http.build();
    }
}
