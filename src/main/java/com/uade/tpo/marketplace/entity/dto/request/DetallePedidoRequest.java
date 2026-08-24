package com.uade.tpo.marketplace.entity.dto.request;

import lombok.Data;

@Data
public class DetallePedidoRequest {
    private int pedidoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;
    private double subtotal;
}
