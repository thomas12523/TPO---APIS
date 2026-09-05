package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Carrito;

import lombok.Data;

@Data
public class CarritoResponse {
    private int carritoId;
    private int usuarioId;
    private String fechaCarrito;
    private boolean activo;

    public CarritoResponse(Carrito carrito) {
        this.carritoId = carrito.getCarritoId();
        this.usuarioId = carrito.getUsuario().getUsuarioId();
        this.fechaCarrito = carrito.getFechaCarrito();
        this.activo = carrito.isActivo();
    }
}
