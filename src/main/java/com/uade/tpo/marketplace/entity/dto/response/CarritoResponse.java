package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Carrito;

import lombok.Data;

@Data
public class CarritoResponse {
    private int carritoId;
    private int usuarioId;
    private String fechaCarrito;
    private boolean activo;

    public static CarritoResponse from(Carrito carrito) {
        CarritoResponse response = new CarritoResponse();
        response.setCarritoId(carrito.getCarritoId());
        response.setUsuarioId(carrito.getUsuario().getUsuarioId());
        response.setFechaCarrito(carrito.getFechaCarrito());
        response.setActivo(carrito.isActivo());
        return response;
    }
}
