package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Pedido;

public class PedidoRepository {
    public ArrayList<Pedido> pedidos = new ArrayList<>();

    public ArrayList<Pedido> getPedidos() {
        return this.pedidos;
    }

    public Pedido getPedidoById(int pedidoId) {
        for (Pedido pedido : this.pedidos) {
            if (pedido.getPedidoId() == pedidoId) {
                return pedido;
            }
        }
        return null;
    }

    public Pedido crearPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        return pedido;
    }

    public Pedido actualizarPedido(int pedidoId, Pedido pedidoActualizado) {
        Pedido pedido = getPedidoById(pedidoId);
        if (pedido == null) {
            return null;
        }
        this.pedidos.remove(pedido);
        this.pedidos.add(pedidoActualizado);
        return pedidoActualizado;
    }

    public boolean deletePedido(int pedidoId) {
        Pedido pedido = getPedidoById(pedidoId);
        if (pedido == null) {
            return false;
        }
        return this.pedidos.remove(pedido);
    }
}
