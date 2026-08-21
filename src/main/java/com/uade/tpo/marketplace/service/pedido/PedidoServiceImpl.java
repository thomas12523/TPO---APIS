package com.uade.tpo.marketplace.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;
import com.uade.tpo.marketplace.repository.IDetallePedidoRepository;
import com.uade.tpo.marketplace.repository.IEnvioRepository;
import com.uade.tpo.marketplace.repository.IPedidoRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;
import com.uade.tpo.marketplace.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IEnvioRepository envioRepository;

    public Page<Pedido> getPedidos(Integer usuarioId, PageRequest pageRequest) {
        if (usuarioId != null)
            return pedidoRepository.findByUsuario_UsuarioId(usuarioId, pageRequest);

        return pedidoRepository.findAll(pageRequest);
    }

    public Optional<Pedido> getPedidoById(int pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public Pedido crearPedido(PedidoRequest pedidoRequest) {
        Usuario usuario = usuarioRepository.findById(pedidoRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

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

        Usuario usuario = usuarioRepository.findById(pedidoRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

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

        List<DetallePedido> items = detallePedidoRepository.findByPedido_PedidoId(pedidoId);
        for (DetallePedido item : items) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() + item.getCantidad());
            productoRepository.save(producto);
        }

        pedido.setEstado("CANCELADO");
        return pedidoRepository.save(pedido);
    }

    public boolean deletePedido(int pedidoId) {
        if (pedidoRepository.findById(pedidoId).isEmpty())
            return false;

        List<DetallePedido> items = detallePedidoRepository.findByPedido_PedidoId(pedidoId);
        for (DetallePedido item : items) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() + item.getCantidad());
            productoRepository.save(producto);
        }
        detallePedidoRepository.deleteAll(items);

        envioRepository.findByPedido_PedidoId(pedidoId).ifPresent(envioRepository::delete);

        pedidoRepository.deleteById(pedidoId);
        return true;
    }
}
