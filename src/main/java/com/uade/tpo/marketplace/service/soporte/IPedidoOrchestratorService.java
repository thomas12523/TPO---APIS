package com.uade.tpo.marketplace.service.soporte;

import java.util.Optional;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.exceptions.PedidoEstadoInvalidoException;
import com.uade.tpo.marketplace.exceptions.PedidoNoCanceladoException;

public interface IPedidoOrchestratorService {

    public Pedido cancelarPedido(int pedidoId);

    public Pedido cancelarPedidoCliente(int pedidoId) throws PedidoEstadoInvalidoException;

    public Pedido cambiarEstado(int pedidoId, String nuevoEstado) throws PedidoEstadoInvalidoException;

    public Optional<Pedido> eliminarPedido(int pedidoId) throws PedidoNoCanceladoException;
}
