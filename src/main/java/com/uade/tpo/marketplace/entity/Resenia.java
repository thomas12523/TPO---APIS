package com.uade.tpo.marketplace.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Resenia {
    private int reseniaId;
    private int usuarioId;
    private int productoId;
    private int calificacion;
    private String comentario;
    private Date fechaCreacion;
}
