package com.uade.tpo.marketplace.service.pedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.PedidoRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioNotFoundException;
import com.uade.tpo.marketplace.repository.IPedidoRepository;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Transactional(readOnly = true)
    public Page<Pedido> getPedidos(Integer usuarioId, PageRequest pageRequest) {
        if (usuarioId != null)
            return pedidoRepository.findByUsuarioId(usuarioId, pageRequest);

        return pedidoRepository.findByActivoTrue(pageRequest);
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> getPedidoById(int pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> getPedidoByNumero(String numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido);
    }

    public Pedido crearPedido(PedidoRequest pedidoRequest) {
        Usuario usuario = usuarioService.getUsuarioById(pedidoRequest.getUsuarioId())
                .orElseThrow(UsuarioNotFoundException::new);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaCreacion(pedidoRequest.getFechaCreacion());
        pedido.setEstado(pedidoRequest.getEstado());
        pedido.setSubtotal(pedidoRequest.getSubtotal());
        pedido.setTotal(pedidoRequest.getTotal());
        pedido.setMetodoPago(pedidoRequest.getMetodoPago());
        pedido = pedidoRepository.save(pedido);
        pedido.setNumeroPedido("PED-" + pedido.getPedidoId());
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(int pedidoId, PedidoRequest pedidoRequest) {
        Optional<Pedido> existente = pedidoRepository.findById(pedidoId);
        if (existente.isEmpty())
            return null;

        Usuario usuario = usuarioService.getUsuarioById(pedidoRequest.getUsuarioId())
                .orElseThrow(UsuarioNotFoundException::new);

        Pedido pedido = existente.get();
        pedido.setUsuario(usuario);
        pedido.setFechaCreacion(pedidoRequest.getFechaCreacion());
        pedido.setEstado(pedidoRequest.getEstado());
        pedido.setSubtotal(pedidoRequest.getSubtotal());
        pedido.setTotal(pedidoRequest.getTotal());
        pedido.setMetodoPago(pedidoRequest.getMetodoPago());
        return pedidoRepository.save(pedido);
    }

    public Pedido cancelarPedido(int pedidoId) {
        Optional<Pedido> existente = pedidoRepository.findById(pedidoId);
        if (existente.isEmpty())
            return null;

        Pedido pedido = existente.get();
        if ("CANCELADO".equals(pedido.getEstado()))
            return pedido;

        pedido.setEstado("CANCELADO");
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> deletePedido(int pedidoId) {
        Optional<Pedido> existente = pedidoRepository.findById(pedidoId);
        if (existente.isEmpty())
            return Optional.empty();

        Pedido pedido = existente.get();
        pedido.setActivo(false);
        return Optional.of(pedidoRepository.save(pedido));
    }
}
