package com.uade.tpo.marketplace.entity.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReseniaRequest {
    private int usuarioId;
    private int productoId;
    private int calificacion;
    private String comentario;
    private Date fechaCreacion;
}
