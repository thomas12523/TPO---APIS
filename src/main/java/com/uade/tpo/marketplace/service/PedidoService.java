package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.repository.PedidoRepository;

public class PedidoService {

    public ArrayList<Pedido> getPedidos() {
        PedidoRepository pedidoRepository = new PedidoRepository();
        return pedidoRepository.getPedidos();
    }

    public Pedido getPedidoById(int pedidoId) {
        PedidoRepository pedidoRepository = new PedidoRepository();
        return pedidoRepository.getPedidoById(pedidoId);
    }

    public Pedido crearPedido(Pedido pedido) {
        PedidoRepository pedidoRepository = new PedidoRepository();
        return pedidoRepository.crearPedido(pedido);
    }

    public Pedido actualizarPedido(int pedidoId, Pedido pedido) {
        PedidoRepository pedidoRepository = new PedidoRepository();
        return pedidoRepository.actualizarPedido(pedidoId, pedido);
    }

    public boolean deletePedido(int pedidoId) {
        PedidoRepository pedidoRepository = new PedidoRepository();
        return pedidoRepository.deletePedido(pedidoId);
    }
}
