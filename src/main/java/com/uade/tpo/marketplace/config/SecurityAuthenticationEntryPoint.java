package com.uade.tpo.marketplace.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        String message;
        if (authException instanceof BadCredentialsException) {
            message = "Usuario o contrasenia incorrectos.";
        } else if (authException instanceof DisabledException) {
            message = "Tu cuenta esta desactivada.";
        } else {
            message = "Por favor, inicia sesion.";
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = String.format(
                "{\"error\": \"%s\", \"status\": %d, \"message\": \"%s\"}",
                "UNAUTHORIZED",
                HttpStatus.UNAUTHORIZED.value(),
                message);

        response.getWriter().write(jsonResponse);
    }
}
