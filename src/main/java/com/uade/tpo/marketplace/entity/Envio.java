package com.uade.tpo.marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {
    private int envioId;
    private int pedidoId;
    private String direccion;
    private String metodoEnvio;
}
