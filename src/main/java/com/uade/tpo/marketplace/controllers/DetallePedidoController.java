package com.uade.tpo.marketplace.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.DetallePedidoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.DetallePedidoResponse;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;

@RestController
@RequestMapping("DetallePedido")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @Autowired
    private IPedidoService pedidoService;

    // un cliente solo puede ver el detalle de sus pedidos; sin pedidoId (todos los detalles) es solo para el admin
    @GetMapping
    public ResponseEntity<List<DetallePedidoResponse>> getDetallesPedido(@AuthenticationPrincipal Usuario usuarioActual,
            @RequestParam(required = false) Integer pedidoId) {
        if (pedidoId == null) {
            if (usuarioActual.getRole() != Role.ADMIN)
                throw new AccessDeniedException("Solo el admin puede ver todos los detalles");
        } else {
            Optional<Pedido> pedido = pedidoService.getPedidoById(pedidoId);
            if (pedido.isEmpty())
                return ResponseEntity.notFound().build();
            pedidoService.validarAcceso(pedido.get(), usuarioActual);
        }

        return ResponseEntity.ok(detallePedidoService.getDetallesPedido(pedidoId).stream()
                .map(DetallePedidoResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedidoResponse> getDetallePedidoById(@AuthenticationPrincipal Usuario usuarioActual,
            @PathVariable int detallePedidoId) {
        Optional<DetallePedido> result = detallePedidoService.getDetallePedidoById(detallePedidoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        pedidoService.validarAcceso(result.get().getPedido(), usuarioActual);
        return ResponseEntity.ok(new DetallePedidoResponse(result.get()));
    }

    @PostMapping
    public ResponseEntity<Object> crearDetallePedido(@RequestBody DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException, StockInsuficienteException {
        DetallePedido result = detallePedidoService.crearDetallePedido(detallePedidoRequest);
        return ResponseEntity.ok(new DetallePedidoResponse(result));
    }

    @PutMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedidoResponse> actualizarDetallePedido(@PathVariable int detallePedidoId, @RequestBody DetallePedidoRequest detallePedidoRequest) throws StockInsuficienteException {
        DetallePedido result = detallePedidoService.actualizarDetallePedido(detallePedidoId, detallePedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DetallePedidoResponse(result));
    }

    @PatchMapping("{detallePedidoId}")
    public ResponseEntity<Object> deleteDetallePedido(@PathVariable int detallePedidoId) {
        Optional<DetallePedido> result = detallePedidoService.deleteDetallePedido(detallePedidoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Detalle de pedido desactivado correctamente", new DetallePedidoResponse(result.get())));
    }
}
