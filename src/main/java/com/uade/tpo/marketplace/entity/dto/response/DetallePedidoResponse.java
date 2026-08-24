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

    public static DetallePedidoResponse from(DetallePedido detallePedido) {
        DetallePedidoResponse response = new DetallePedidoResponse();
        response.setDetallePedidoId(detallePedido.getDetallePedidoId());
        response.setPedidoId(detallePedido.getPedido().getPedidoId());
        response.setProductoId(detallePedido.getProducto().getProductoId());
        response.setProductoNombre(detallePedido.getProducto().getNombreProducto());
        response.setCantidad(detallePedido.getCantidad());
        response.setPrecioUnitario(detallePedido.getPrecioUnitario());
        response.setObservaciones(detallePedido.getObservaciones());
        response.setSubtotal(detallePedido.getSubtotal());
        return response;
    }
}
