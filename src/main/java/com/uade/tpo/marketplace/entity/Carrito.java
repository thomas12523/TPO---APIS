package com.uade.tpo.marketplace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
    private int carritoId;
    private int usuarioId;
    private String fechaAt;
}
