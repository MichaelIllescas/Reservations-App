package com.reservastrenque.reservas_trenque.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

/**
 * Configuración de seguridad para la aplicación.
 * Aquí se definen las reglas de acceso a las rutas y cómo manejar la autenticación y autorización.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtCookieAuthenticationFilter jwtCookieAuthenticationFilter;  // Filtro para manejar la autenticación mediante cookies.
    private final AuthenticationProvider authenticationProvider;  // Proveedor de autenticación.
    private final SecurityProperties securityProperties;
    /**
     * Configura el filtrado de seguridad para la aplicación web.
     *
     * @param http objeto HttpSecurity que permite configurar los filtros y reglas de seguridad.
     * @return SecurityFilterChain con la configuración de seguridad.
     * @throws Exception en caso de error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOriginPatterns(securityProperties.getCorsAllowedOrigins());
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(securityProperties.getWhitelist().toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtCookieAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
