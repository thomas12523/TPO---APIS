package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.uade.tpo.marketplace.entity.dto.request.PedidoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.PedidoResponse;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.soporte.IPedidoOrchestratorService;

@RestController
@RequestMapping("Pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IPedidoOrchestratorService pedidoOrchestratorService;

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> getPedidos(
            @RequestParam(required = false) Integer usuarioId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(pedidoService.getPedidos(usuarioId, PageRequest.of(page, size)).map(PedidoResponse::from));
    }

    @GetMapping("{pedidoId}")
    public ResponseEntity<PedidoResponse> getPedidoById(@PathVariable int pedidoId) {
        Optional<Pedido> result = pedidoService.getPedidoById(pedidoId);
        if (result.isPresent())
            return ResponseEntity.ok(PedidoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("numero/{numeroPedido}")
    public ResponseEntity<PedidoResponse> getPedidoByNumero(@PathVariable String numeroPedido) {
        Optional<Pedido> result = pedidoService.getPedidoByNumero(numeroPedido);
        if (result.isPresent())
            return ResponseEntity.ok(PedidoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.crearPedido(pedidoRequest);
        return ResponseEntity.ok(PedidoResponse.from(result));
    }

    @PutMapping("{pedidoId}")
    public ResponseEntity<PedidoResponse> actualizarPedido(@PathVariable int pedidoId, @RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.actualizarPedido(pedidoId, pedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PedidoResponse.from(result));
    }

    @PostMapping("{pedidoId}/cancelar")
    public ResponseEntity<PedidoResponse> cancelarPedido(@PathVariable int pedidoId) {
        Pedido result = pedidoOrchestratorService.cancelarPedido(pedidoId);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PedidoResponse.from(result));
    }

    @DeleteMapping("{pedidoId}")
    public ResponseEntity<Object> deletePedido(@PathVariable int pedidoId) {
        Optional<Pedido> result = pedidoOrchestratorService.eliminarPedido(pedidoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Pedido desactivado correctamente", PedidoResponse.from(result.get())));
    }
}
