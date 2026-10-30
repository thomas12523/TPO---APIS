package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.PedidoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.PedidoResponse;
import com.uade.tpo.marketplace.exceptions.PedidoEstadoInvalidoException;
import com.uade.tpo.marketplace.exceptions.PedidoNoCanceladoException;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.soporte.IPedidoOrchestratorService;

@RestController
@RequestMapping("Pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IPedidoOrchestratorService pedidoOrchestratorService;

    @GetMapping // el admin ve los de todos (o filtra por usuarioId); un cliente siempre ve solo los suyos
    public ResponseEntity<Page<PedidoResponse>> getPedidos(@AuthenticationPrincipal Usuario usuarioActual,
            @RequestParam(required = false) Integer usuarioId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        if (usuarioActual.getRole() != Role.ADMIN)
            usuarioId = usuarioActual.getUsuarioId();

        return ResponseEntity.ok(pedidoService.getPedidos(usuarioId, PageRequest.of(page, size)).map(PedidoResponse::new));
    }

    @GetMapping("{pedidoId}")
    public ResponseEntity<PedidoResponse> getPedidoById(@AuthenticationPrincipal Usuario usuarioActual, @PathVariable int pedidoId) {
        Optional<Pedido> result = pedidoService.getPedidoById(pedidoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        pedidoService.validarAcceso(result.get(), usuarioActual);
        return ResponseEntity.ok(new PedidoResponse(result.get()));
    }

    @GetMapping("numero/{numeroPedido}")
    public ResponseEntity<PedidoResponse> getPedidoByNumero(@AuthenticationPrincipal Usuario usuarioActual, @PathVariable String numeroPedido) {
        Optional<Pedido> result = pedidoService.getPedidoByNumero(numeroPedido);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        pedidoService.validarAcceso(result.get(), usuarioActual);
        return ResponseEntity.ok(new PedidoResponse(result.get()));
    }

    @PostMapping
    public ResponseEntity<Object> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.crearPedido(pedidoRequest);
        return ResponseEntity.ok(new PedidoResponse(result));
    }

    @PutMapping("{pedidoId}")
    public ResponseEntity<PedidoResponse> actualizarPedido(@PathVariable int pedidoId, @RequestBody PedidoRequest pedidoRequest) {
        Pedido result = pedidoService.actualizarPedido(pedidoId, pedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PedidoResponse(result));
    }

    @PostMapping("{pedidoId}/cancelar") // el cliente solo puede cancelar si el pedido esta PENDIENTE
    public ResponseEntity<PedidoResponse> cancelarPedido(@AuthenticationPrincipal Usuario usuarioActual, @PathVariable int pedidoId)
            throws PedidoEstadoInvalidoException {
        Optional<Pedido> pedido = pedidoService.getPedidoById(pedidoId);
        if (pedido.isEmpty())
            return ResponseEntity.notFound().build();

        pedidoService.validarAcceso(pedido.get(), usuarioActual);
        Pedido result = pedidoOrchestratorService.cancelarPedidoCliente(pedidoId);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PedidoResponse(result));
    }

    @PatchMapping("{pedidoId}/estado") // solo ROLE_ADMIN: PENDIENTE -> ACEPTADO -> ENVIADO -> ENTREGADO (o CANCELADO)
    public ResponseEntity<PedidoResponse> cambiarEstado(@PathVariable int pedidoId, @RequestParam String estado)
            throws PedidoEstadoInvalidoException {
        Pedido result = pedidoOrchestratorService.cambiarEstado(pedidoId, estado);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PedidoResponse(result));
    }

    @PatchMapping("{pedidoId}")
    public ResponseEntity<Object> deletePedido(@PathVariable int pedidoId) throws PedidoNoCanceladoException {
        Optional<Pedido> result = pedidoOrchestratorService.eliminarPedido(pedidoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Pedido desactivado correctamente", new PedidoResponse(result.get())));
    }
}
