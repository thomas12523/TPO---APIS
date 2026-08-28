package com.uade.tpo.marketplace.controllers.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String contrasenia;
}
