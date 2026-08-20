package com.uade.tpo.marketplace.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;
import com.uade.tpo.marketplace.repository.PedidoRepository;
import com.uade.tpo.marketplace.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(int pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public Pedido crearPedido(PedidoRequest pedidoRequest) {
        Usuario usuario = usuarioRepository.findById(pedidoRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .fechaCreacion(pedidoRequest.getFechaCreacion())
                .estado(pedidoRequest.getEstado())
                .subtotal(pedidoRequest.getSubtotal())
                .total(pedidoRequest.getTotal())
                .metodoPago(pedidoRequest.getMetodoPago())
                .build();
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

    public boolean deletePedido(int pedidoId) {
        if (pedidoRepository.findById(pedidoId).isEmpty())
            return false;

        pedidoRepository.deleteById(pedidoId);
        return true;
    }
}
