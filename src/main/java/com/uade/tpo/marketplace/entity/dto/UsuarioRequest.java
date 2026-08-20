package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class UsuarioRequest {
    private int dni;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
}
