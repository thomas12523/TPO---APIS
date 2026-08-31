package com.uade.tpo.marketplace.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.dto.request.DetallePedidoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DetallePedidoResponse;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;

@RestController
@RequestMapping("DetallePedido")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponse>> getDetallesPedido(@RequestParam(required = false) Integer pedidoId) {
        return ResponseEntity.ok(detallePedidoService.getDetallesPedido(pedidoId).stream()
                .map(DetallePedidoResponse::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedidoResponse> getDetallePedidoById(@PathVariable int detallePedidoId) {
        Optional<DetallePedido> result = detallePedidoService.getDetallePedidoById(detallePedidoId);
        if (result.isPresent())
            return ResponseEntity.ok(DetallePedidoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearDetallePedido(@RequestBody DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        DetallePedido result = detallePedidoService.crearDetallePedido(detallePedidoRequest);
        return ResponseEntity.ok(DetallePedidoResponse.from(result));
    }

    @PutMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedidoResponse> actualizarDetallePedido(@PathVariable int detallePedidoId, @RequestBody DetallePedidoRequest detallePedidoRequest) {
        DetallePedido result = detallePedidoService.actualizarDetallePedido(detallePedidoId, detallePedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(DetallePedidoResponse.from(result));
    }

    @DeleteMapping("{detallePedidoId}")
    public ResponseEntity<Void> deleteDetallePedido(@PathVariable int detallePedidoId) {
        boolean deleted = detallePedidoService.deleteDetallePedido(detallePedidoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
