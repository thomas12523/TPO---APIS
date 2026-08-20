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
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.dto.DetallePedidoRequest;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.service.detallepedido.DetallePedidoService;

@RestController
@RequestMapping("DetallePedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> getDetallesPedido() {
        return ResponseEntity.ok(detallePedidoService.getDetallesPedido());
    }

    @GetMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedido> getDetallePedidoById(@PathVariable int detallePedidoId) {
        Optional<DetallePedido> result = detallePedidoService.getDetallePedidoById(detallePedidoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearDetallePedido(@RequestBody DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        DetallePedido result = detallePedidoService.crearDetallePedido(detallePedidoRequest);
        return ResponseEntity.created(URI.create("/DetallePedido/" + result.getDetallePedidoId())).body(result);
    }

    @PutMapping("{detallePedidoId}")
    public ResponseEntity<DetallePedido> actualizarDetallePedido(@PathVariable int detallePedidoId, @RequestBody DetallePedidoRequest detallePedidoRequest) {
        DetallePedido result = detallePedidoService.actualizarDetallePedido(detallePedidoId, detallePedidoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{detallePedidoId}")
    public ResponseEntity<Void> deleteDetallePedido(@PathVariable int detallePedidoId) {
        boolean deleted = detallePedidoService.deleteDetallePedido(detallePedidoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
