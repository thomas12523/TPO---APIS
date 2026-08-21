package com.uade.tpo.marketplace.service.pedido;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;

public interface IPedidoService {

    public Page<Pedido> getPedidos(Integer usuarioId, PageRequest pageRequest);

    public Optional<Pedido> getPedidoById(int pedidoId);

    public Pedido crearPedido(PedidoRequest pedidoRequest);

    public Pedido actualizarPedido(int pedidoId, PedidoRequest pedidoRequest);

    public Pedido cancelarPedido(int pedidoId);

    public boolean deletePedido(int pedidoId);
}
