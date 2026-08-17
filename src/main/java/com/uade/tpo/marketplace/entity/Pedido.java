package com.uade.tpo.marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int pedidoId;
    private int usuarioId;
    private String fechaCreacion;
    private String estado;
    private double subtotal;
    private double costoEnvio;
    private double total;
}
