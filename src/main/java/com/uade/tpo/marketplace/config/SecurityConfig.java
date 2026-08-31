package com.uade.tpo.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final SecurityAuthenticationEntryPoint authenticationEntryPoint;
    private final SecurityExceptionHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        // Auth: publico
                        .requestMatchers(HttpMethod.POST, "/Auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Auth/authenticate").permitAll()

                        // Catalogo: lectura publica
                        .requestMatchers(HttpMethod.GET, "/Producto").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Producto/{productoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Categories/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Imagen").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Imagen/{imagenId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Descuento").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Descuento/{descuentoId}").permitAll()

                        // Producto: alta/gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Producto").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Producto/{productoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Producto/{productoId}").hasAuthority("ROLE_ADMIN")

                        // Categories: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Categories").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Categories/{categoryId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Categories/{categoryId}").hasAuthority("ROLE_ADMIN")

                        // Imagen: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Imagen").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/Imagen/upload").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Imagen/{imagenId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Imagen/{imagenId}").hasAuthority("ROLE_ADMIN")

                        // Descuento: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Descuento").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Descuento/{descuentoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Descuento/{descuentoId}").hasAuthority("ROLE_ADMIN")

                        // Usuario: administracion de cuentas solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.GET, "/Usuario").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/Usuario").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Usuario/{usuarioId}/permisos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Usuario/{usuarioId}").hasAuthority("ROLE_ADMIN")

                        // Todo lo demas: cualquier usuario autenticado (Carrito, DetalleCarrito,
                        // Pedido, DetallePedido, perfil propio de Usuario)
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler));

        return http.build();
    }
}
