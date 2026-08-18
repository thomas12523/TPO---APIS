package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class EnvioRequest {
    private int envioId;
    private int pedidoId;
    private String direccion;
    private String metodoEnvio;
    private double costoEnvio;
}
