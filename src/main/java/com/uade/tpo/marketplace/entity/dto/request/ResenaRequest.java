package com.uade.tpo.marketplace.entity.dto.request;

import lombok.Data;

@Data
public class ResenaRequest {
    private int productoId;
    private int puntuacion;
    private String comentario;
}
