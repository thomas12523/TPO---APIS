package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.PedidoRequest;
import com.uade.tpo.marketplace.service.pedido.PedidoService;

@RestController
@RequestMapping("Pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(@RequestParam(required = false) Integer usuarioId) {
        return ResponseEntity.ok(pedidoService.getPedidos(usuarioId));
    }

    @GetMapping("{pedidoId}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int pedidoId) {
        Optional<Pedido> result = pedidoService.getPedidoById(pedidoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.crearPedido(pedidoRequest);
        return ResponseEntity.created(URI.create("/Pedido/" + result.getPedidoId())).body(result);
    }

    @PutMapping("{pedidoId}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable int pedidoId, @RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.actualizarPedido(pedidoId, pedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @PostMapping("{pedidoId}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable int pedidoId) {
        Pedido result = pedidoService.cancelarPedido(pedidoId);
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
