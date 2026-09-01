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

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.dto.request.DetalleCarritoRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.DetalleCarritoResponse;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.service.detallecarrito.IDetalleCarritoService;

@RestController
@RequestMapping("DetalleCarrito")
public class DetalleCarritoController {

    @Autowired
    private IDetalleCarritoService detalleCarritoService;

    @GetMapping
    public ResponseEntity<List<DetalleCarritoResponse>> getDetallesCarrito(@RequestParam(required = false) Integer carritoId) {
        return ResponseEntity.ok(detalleCarritoService.getDetallesCarrito(carritoId).stream()
                .map(DetalleCarritoResponse::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("{carritoId}/{productoId}")
    public ResponseEntity<DetalleCarritoResponse> getDetalleCarritoById(@PathVariable int carritoId, @PathVariable int productoId) {
        Optional<DetalleCarrito> result = detalleCarritoService.getDetalleCarritoById(carritoId, productoId);
        if (result.isPresent())
            return ResponseEntity.ok(DetalleCarritoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearDetalleCarrito(@RequestBody DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException, StockInsuficienteException {
        DetalleCarrito result = detalleCarritoService.crearDetalleCarrito(detalleCarritoRequest);
        return ResponseEntity.ok(DetalleCarritoResponse.from(result));
    }

    @PutMapping("{carritoId}/{productoId}")
    public ResponseEntity<DetalleCarritoResponse> actualizarDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId, @RequestBody DetalleCarritoRequest detalleCarritoRequest) throws StockInsuficienteException {
        DetalleCarrito result = detalleCarritoService.actualizarDetalleCarrito(carritoId, productoId, detalleCarritoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(DetalleCarritoResponse.from(result));
    }

    @DeleteMapping("{carritoId}/{productoId}")
    public ResponseEntity<Object> deleteDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId) {
        Optional<DetalleCarrito> result = detalleCarritoService.deleteDetalleCarrito(carritoId, productoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Detalle de carrito eliminado correctamente", DetalleCarritoResponse.from(result.get())));
    }
}
