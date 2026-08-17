package com.uade.tpo.marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarrito {
    private int carritoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
}
