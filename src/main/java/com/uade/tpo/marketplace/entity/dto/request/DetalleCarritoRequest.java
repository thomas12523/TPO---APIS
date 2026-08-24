package com.uade.tpo.marketplace.entity.dto.request;

import lombok.Data;

@Data
public class DetalleCarritoRequest {
    private int carritoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
}
