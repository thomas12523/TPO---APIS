package com.uade.tpo.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final SecurityAuthenticationEntryPoint authenticationEntryPoint;
    private final SecurityExceptionHandler accessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider,
            SecurityAuthenticationEntryPoint authenticationEntryPoint,
            SecurityExceptionHandler accessDeniedHandler) {
        super();
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        // Auth: publico
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Auth/authenticate").permitAll()

                        // Catalogo: lectura publica
                        .requestMatchers(HttpMethod.GET, "/Producto").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Producto/{productoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Categories/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Imagen").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Imagen/{imagenId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Imagen/principal/{productoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Descuento").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Descuento/{descuentoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Resena").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Resena/{resenaId}").permitAll()

                        // Producto: alta/gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Producto").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Producto/{productoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Producto/{productoId}/stock").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Producto/{productoId}/estado").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Producto/{productoId}").hasAuthority("ROLE_ADMIN")

                        // Categories: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Categories").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Categories/{categoryId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Categories/{categoryId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Categories/{categoryId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Categories/{categoryId}/estado").hasAuthority("ROLE_ADMIN")

                        // Imagen: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Imagen/upload").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Imagen/{imagenId}").hasAuthority("ROLE_ADMIN")

                        // Descuento: gestion solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/Descuento").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Descuento/{descuentoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Descuento/{descuentoId}").hasAuthority("ROLE_ADMIN")

                        // Usuario: administracion de cuentas solo vendedor (ROLE_ADMIN)
                        .requestMatchers(HttpMethod.GET, "/Usuario").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/Usuario").hasAuthority("ROLE_ADMIN")
                        // Usuario: perfil propio (cualquier autenticado)
                        .requestMatchers(HttpMethod.GET, "/Usuario/me").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/Usuario/me").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/Usuario/me/contrasenia").authenticated()
                        .requestMatchers(HttpMethod.GET, "/Usuario/{usuarioId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Usuario/{usuarioId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Usuario/{usuarioId}/permisos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Usuario/{usuarioId}/estado").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Usuario/{usuarioId}").hasAuthority("ROLE_ADMIN")

                        // Pedido: cambiar el estado y el ABM a mano solo vendedor (ROLE_ADMIN).
                        // Los clientes crean pedidos con el checkout y solo ven/cancelan los suyos (lo valida el controller)
                        .requestMatchers(HttpMethod.PATCH, "/Pedido/{pedidoId}/estado").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/Pedido").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Pedido/{pedidoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/Pedido/{pedidoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/DetallePedido").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/DetallePedido/{detallePedidoId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/DetallePedido/{detallePedidoId}").hasAuthority("ROLE_ADMIN")

                        // Todo lo demas: cualquier usuario autenticado (Carrito, DetalleCarrito,
                        // Pedido, DetallePedido)
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
