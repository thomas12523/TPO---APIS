package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Pedido;

public class PedidoRepository {
    private ArrayList<Pedido> pedidos;

    public PedidoRepository() {
        pedidos = new ArrayList<Pedido>();
    }

    public ArrayList<Pedido> getPedidos() {
        return this.pedidos;
    }

    public Optional<Pedido> getPedidoById(int pedidoId) {
        return this.pedidos.stream().filter(p -> p.getPedidoId() == pedidoId).findAny();
    }

    public Pedido crearPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        return pedido;
    }

    public Pedido actualizarPedido(int pedidoId, Pedido pedidoActualizado) {
        Optional<Pedido> pedido = getPedidoById(pedidoId);
        if (pedido.isEmpty()) {
            return null;
        }
        this.pedidos.remove(pedido.get());
        this.pedidos.add(pedidoActualizado);
        return pedidoActualizado;
    }

    public boolean deletePedido(int pedidoId) {
        Optional<Pedido> pedido = getPedidoById(pedidoId);
        if (pedido.isEmpty()) {
            return false;
        }
        return this.pedidos.remove(pedido.get());
    }
}
