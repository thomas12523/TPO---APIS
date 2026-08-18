package com.uade.tpo.marketplace.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Envio {
    private int envioId;
    private int pedidoId;
    private String direccion;
    private String metodoEnvio;
    private double costoEnvio;
}
