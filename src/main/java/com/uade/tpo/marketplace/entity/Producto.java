package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private int productoId;
    private int categoriaId;
    private String nombreProducto;
    private String descripcion;
    private double precioUnitario;
    private int stock;
    private String imagenUrl;
}
