package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Pedido;

import lombok.Data;

@Data
public class PedidoResponse {
    private int pedidoId;
    private String numeroPedido;
    private int usuarioId;
    private String fechaCreacion;
    private String estado;
    private double subtotal;
    private double total;
    private String metodoPago;

    public static PedidoResponse from(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setPedidoId(pedido.getPedidoId());
        response.setNumeroPedido(pedido.getNumeroPedido());
        response.setUsuarioId(pedido.getUsuario().getUsuarioId());
        response.setFechaCreacion(pedido.getFechaCreacion());
        response.setEstado(pedido.getEstado());
        response.setSubtotal(pedido.getSubtotal());
        response.setTotal(pedido.getTotal());
        response.setMetodoPago(pedido.getMetodoPago());
        return response;
    }
}
