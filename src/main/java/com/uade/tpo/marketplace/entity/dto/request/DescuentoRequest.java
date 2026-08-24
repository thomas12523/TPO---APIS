package com.uade.tpo.marketplace.entity.dto.request;

import lombok.Data;

@Data
public class DescuentoRequest {
    private int productoId;
    private double porcentaje;
    private boolean activo;
    private String fechaInicio;
    private String fechaFin;
}
