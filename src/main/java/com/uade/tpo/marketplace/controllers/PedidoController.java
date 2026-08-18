package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;
import com.uade.tpo.marketplace.exceptions.PedidoDuplicateException;
import com.uade.tpo.marketplace.service.PedidoService;

@RestController
@RequestMapping("Pedido")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController() {
        pedidoService = new PedidoService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Pedido>> getPedidos() {
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping("{pedidoId}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int pedidoId) {
        Optional<Pedido> result = pedidoService.getPedidoById(pedidoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearPedido(@RequestBody PedidoRequest pedidoRequest) throws PedidoDuplicateException {
        Pedido pedido = Pedido.builder()
                .pedidoId(pedidoRequest.getPedidoId())
                .usuarioId(pedidoRequest.getUsuarioId())
                .fechaCreacion(pedidoRequest.getFechaCreacion())
                .estado(pedidoRequest.getEstado())
                .subtotal(pedidoRequest.getSubtotal())
                .total(pedidoRequest.getTotal())
                .metodoPago(pedidoRequest.getMetodoPago())
                .build();
        Pedido result = pedidoService.crearPedido(pedido);
        return ResponseEntity.created(URI.create("/Pedido/" + result.getPedidoId())).body(result);
    }

    @PutMapping("{pedidoId}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable int pedidoId, @RequestBody Pedido entity) {
        Pedido result = pedidoService.actualizarPedido(pedidoId, entity);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{pedidoId}")
    public ResponseEntity<Void> deletePedido(@PathVariable int pedidoId) {
        boolean deleted = pedidoService.deletePedido(pedidoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
