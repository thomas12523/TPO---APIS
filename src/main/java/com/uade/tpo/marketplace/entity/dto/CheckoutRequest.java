package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class CheckoutRequest {
    private String metodoPago;
    private String direccion;
    private String metodoEnvio;
    private double costoEnvio;
}
