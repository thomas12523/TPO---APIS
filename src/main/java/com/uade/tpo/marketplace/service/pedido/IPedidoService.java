package com.uade.tpo.marketplace.service.pedido;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.PedidoRequest;
import com.uade.tpo.marketplace.exceptions.PedidoNoCanceladoException;

public interface IPedidoService {

    public Page<Pedido> getPedidos(Integer usuarioId, PageRequest pageRequest);

    public Optional<Pedido> getPedidoById(int pedidoId);

    public Optional<Pedido> getPedidoByNumero(String numeroPedido);

    public Pedido crearPedido(PedidoRequest pedidoRequest);

    public Pedido actualizarPedido(int pedidoId, PedidoRequest pedidoRequest);

    public Pedido cancelarPedido(int pedidoId);

    public Pedido actualizarEstado(int pedidoId, String estado);

    public void validarAcceso(Pedido pedido, Usuario usuarioActual);

    public Optional<Pedido> deletePedido(int pedidoId) throws PedidoNoCanceladoException;
}
