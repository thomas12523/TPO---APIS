package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class PedidoRequest {
    private int pedidoId;
    private int usuarioId;
    private String fechaCreacion;
    private String estado;
    private double subtotal;
    private double total;
    private String metodoPago;
}
