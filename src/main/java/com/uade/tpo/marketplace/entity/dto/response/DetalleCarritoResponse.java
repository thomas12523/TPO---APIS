package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.DetalleCarrito;

import lombok.Data;

@Data
public class DetalleCarritoResponse {
    private int detalleCarritoId;
    private int carritoId;
    private int productoId;
    private String productoNombre;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private boolean activo;

    public static DetalleCarritoResponse from(DetalleCarrito detalleCarrito) {
        DetalleCarritoResponse response = new DetalleCarritoResponse();
        response.setDetalleCarritoId(detalleCarrito.getDetalleCarritoId());
        response.setCarritoId(detalleCarrito.getCarrito().getCarritoId());
        response.setProductoId(detalleCarrito.getProducto().getProductoId());
        response.setProductoNombre(detalleCarrito.getProducto().getNombreProducto());
        response.setCantidad(detalleCarrito.getCantidad());
        response.setPrecioUnitario(detalleCarrito.getPrecioUnitario());
        response.setSubtotal(detalleCarrito.getCantidad() * detalleCarrito.getPrecioUnitario());
        response.setActivo(detalleCarrito.isActivo());
        return response;
    }
}
