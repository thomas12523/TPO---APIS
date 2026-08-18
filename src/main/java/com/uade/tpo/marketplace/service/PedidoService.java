package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.exceptions.PedidoDuplicateException;
import com.uade.tpo.marketplace.repository.PedidoRepository;

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService() {
        pedidoRepository = new PedidoRepository();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidoRepository.getPedidos();
    }

    public Optional<Pedido> getPedidoById(int pedidoId) {
        return pedidoRepository.getPedidoById(pedidoId);
    }

    public Pedido crearPedido(Pedido pedido) throws PedidoDuplicateException {
        if (pedidoRepository.getPedidoById(pedido.getPedidoId()).isPresent())
            throw new PedidoDuplicateException();
        return pedidoRepository.crearPedido(pedido);
    }

    public Pedido actualizarPedido(int pedidoId, Pedido pedido) {
        return pedidoRepository.actualizarPedido(pedidoId, pedido);
    }

    public boolean deletePedido(int pedidoId) {
        return pedidoRepository.deletePedido(pedidoId);
    }
}
