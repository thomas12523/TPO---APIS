package com.uade.tpo.marketplace.service.soporte;

import java.util.Optional;

import com.uade.tpo.marketplace.entity.Pedido;

public interface IPedidoOrchestratorService {

    public Pedido cancelarPedido(int pedidoId);

    public Optional<Pedido> eliminarPedido(int pedidoId);
}
