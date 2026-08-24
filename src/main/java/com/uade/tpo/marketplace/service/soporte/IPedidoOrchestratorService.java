package com.uade.tpo.marketplace.service.soporte;

import com.uade.tpo.marketplace.entity.Pedido;

public interface IPedidoOrchestratorService {

    public Pedido cancelarPedido(int pedidoId);

    public boolean eliminarPedido(int pedidoId);
}
