package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private int usuarioId;
    private int dni;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
}
