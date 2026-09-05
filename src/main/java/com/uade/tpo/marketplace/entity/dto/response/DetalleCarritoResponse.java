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

    public DetalleCarritoResponse(DetalleCarrito detalleCarrito) {
        this.detalleCarritoId = detalleCarrito.getDetalleCarritoId();
        this.carritoId = detalleCarrito.getCarrito().getCarritoId();
        this.productoId = detalleCarrito.getProducto().getProductoId();
        this.productoNombre = detalleCarrito.getProducto().getNombreProducto();
        this.cantidad = detalleCarrito.getCantidad();
        this.precioUnitario = detalleCarrito.getPrecioUnitario();
        this.subtotal = detalleCarrito.getCantidad() * detalleCarrito.getPrecioUnitario();
        this.activo = detalleCarrito.isActivo();
    }
}
