package com.uade.tpo.marketplace.service.pedido;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;

public interface PedidoService {

    public List<Pedido> getPedidos();

    public Optional<Pedido> getPedidoById(int pedidoId);

    public Pedido crearPedido(PedidoRequest pedidoRequest);

    public Pedido actualizarPedido(int pedidoId, PedidoRequest pedidoRequest);

    public boolean deletePedido(int pedidoId);
}
