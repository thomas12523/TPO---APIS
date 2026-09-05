package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.DetallePedido;

import lombok.Data;

@Data
public class DetallePedidoResponse {
    private int detallePedidoId;
    private int pedidoId;
    private int productoId;
    private String productoNombre;
    private int cantidad;
    private double precioUnitario;
    private String observaciones;
    private double subtotal;
    private boolean activo;

    public DetallePedidoResponse(DetallePedido detallePedido) {
        this.detallePedidoId = detallePedido.getDetallePedidoId();
        this.pedidoId = detallePedido.getPedido().getPedidoId();
        this.productoId = detallePedido.getProducto().getProductoId();
        this.productoNombre = detallePedido.getProducto().getNombreProducto();
        this.cantidad = detallePedido.getCantidad();
        this.precioUnitario = detallePedido.getPrecioUnitario();
        this.observaciones = detallePedido.getObservaciones();
        this.subtotal = detallePedido.getSubtotal();
        this.activo = detallePedido.isActivo();
    }
}
