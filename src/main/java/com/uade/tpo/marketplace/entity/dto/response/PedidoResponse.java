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
    private boolean activo;

    public PedidoResponse(Pedido pedido) {
        this.pedidoId = pedido.getPedidoId();
        this.numeroPedido = pedido.getNumeroPedido();
        this.usuarioId = pedido.getUsuario().getUsuarioId();
        this.fechaCreacion = pedido.getFechaCreacion();
        this.estado = pedido.getEstado();
        this.subtotal = pedido.getSubtotal();
        this.total = pedido.getTotal();
        this.metodoPago = pedido.getMetodoPago();
        this.activo = pedido.isActivo();
    }
}
