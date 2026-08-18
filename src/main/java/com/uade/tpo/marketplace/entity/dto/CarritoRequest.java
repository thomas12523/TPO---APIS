package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class CarritoRequest {
    private int carritoId;
    private int usuarioId;
    private String fechaCarrito;
}
