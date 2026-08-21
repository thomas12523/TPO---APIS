package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class ProductoRequest {
    private int categoriaId;
    private String nombreProducto;
    private String descripcion;
    private double precioUnitario;
    private int stock;
    private String imagenUrl;
    private double descuentoPorcentaje;
}
