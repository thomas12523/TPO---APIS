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

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.dto.DetalleCarritoRequest;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.service.detallecarrito.DetalleCarritoService;

@RestController
@RequestMapping("DetalleCarrito")
public class DetalleCarritoController {

    @Autowired
    private DetalleCarritoService detalleCarritoService;

    @GetMapping
    public ResponseEntity<List<DetalleCarrito>> getDetallesCarrito() {
        return ResponseEntity.ok(detalleCarritoService.getDetallesCarrito());
    }

    @GetMapping("{carritoId}/{productoId}")
    public ResponseEntity<DetalleCarrito> getDetalleCarritoById(@PathVariable int carritoId, @PathVariable int productoId) {
        Optional<DetalleCarrito> result = detalleCarritoService.getDetalleCarritoById(carritoId, productoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearDetalleCarrito(@RequestBody DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException {
        DetalleCarrito result = detalleCarritoService.crearDetalleCarrito(detalleCarritoRequest);
        return ResponseEntity.created(URI.create("/DetalleCarrito/" + result.getCarrito().getCarritoId() + "/" + result.getProducto().getProductoId())).body(result);
    }

    @PutMapping("{carritoId}/{productoId}")
    public ResponseEntity<DetalleCarrito> actualizarDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId, @RequestBody DetalleCarritoRequest detalleCarritoRequest) {
        DetalleCarrito result = detalleCarritoService.actualizarDetalleCarrito(carritoId, productoId, detalleCarritoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{carritoId}/{productoId}")
    public ResponseEntity<Void> deleteDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId) {
        boolean deleted = detalleCarritoService.deleteDetalleCarrito(carritoId, productoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
