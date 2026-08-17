package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pedido {
    private int pedidoId;
    private int usuarioId;
    private String fechaCreacion;
    private String estado;
    private double subtotal;
    private double costoEnvio;
    private double total;
}
