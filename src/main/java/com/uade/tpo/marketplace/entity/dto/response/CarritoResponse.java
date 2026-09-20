package com.uade.tpo.marketplace.entity.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.DetalleCarrito;

import lombok.Data;

@Data
public class CarritoResponse {
    private int carritoId;
    private int usuarioId;
    private String fechaCarrito;
    private boolean activo;
    private List<DetalleCarritoResponse> items;
    private double subtotal;
    private double total;

    public CarritoResponse(Carrito carrito) {
        this.carritoId = carrito.getCarritoId();
        this.usuarioId = carrito.getUsuario().getUsuarioId();
        this.fechaCarrito = carrito.getFechaCarrito();
        this.activo = carrito.isActivo();
    }

    public CarritoResponse(Carrito carrito, List<DetalleCarrito> detalles) {
        this(carrito);
        this.items = detalles.stream().map(DetalleCarritoResponse::new).collect(Collectors.toList());
        this.subtotal = detalles.stream()
                .mapToDouble(detalle -> detalle.getCantidad() * detalle.getProducto().getPrecioUnitario())
                .sum();
        this.total = detalles.stream()
                .mapToDouble(detalle -> detalle.getCantidad() * detalle.getPrecioUnitario())
                .sum();
    }
}
