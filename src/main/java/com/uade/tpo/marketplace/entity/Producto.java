package com.uade.tpo.marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private int productoId;
    private int categoriaId;
    private String nombreProducto;
    private String descripcion;
    private double precioUnitario;
    private int stock;
}
