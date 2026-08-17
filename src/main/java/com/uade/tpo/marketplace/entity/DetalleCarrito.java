package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleCarrito {
    private int carritoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
}
