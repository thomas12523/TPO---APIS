package com.uade.tpo.marketplace.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;
import com.uade.tpo.marketplace.repository.IPedidoRepository;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @Autowired
    private IProductoService productoService;

    public Page<Pedido> getPedidos(Integer usuarioId, PageRequest pageRequest) {
        if (usuarioId != null)
            return pedidoRepository.findByUsuarioId(usuarioId, pageRequest);

        return pedidoRepository.findAll(pageRequest);
    }

    public Optional<Pedido> getPedidoById(int pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public Pedido crearPedido(PedidoRequest pedidoRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(pedidoRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaCreacion(pedidoRequest.getFechaCreacion());
        pedido.setEstado(pedidoRequest.getEstado());
        pedido.setSubtotal(pedidoRequest.getSubtotal());
        pedido.setTotal(pedidoRequest.getTotal());
        pedido.setMetodoPago(pedidoRequest.getMetodoPago());
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(int pedidoId, PedidoRequest pedidoRequest) {
        Optional<Pedido> existente = pedidoRepository.findById(pedidoId);
        if (existente.isEmpty())
            return null;

        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(pedidoRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

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

        List<DetallePedido> items = detallePedidoService.getDetallesPedido(pedidoId);
        for (DetallePedido item : items) {
            productoService.ajustarStock(item.getProducto().getProductoId(), item.getCantidad());
        }

        pedido.setEstado("CANCELADO");
        return pedidoRepository.save(pedido);
    }

    public boolean deletePedido(int pedidoId) {
        if (pedidoRepository.findById(pedidoId).isEmpty())
            return false;

        List<DetallePedido> items = detallePedidoService.getDetallesPedido(pedidoId);
        for (DetallePedido item : items) {
            productoService.ajustarStock(item.getProducto().getProductoId(), item.getCantidad());
        }
        detallePedidoService.deleteDetallesByPedidoId(pedidoId);

        pedidoRepository.deleteById(pedidoId);
        return true;
    }
}
