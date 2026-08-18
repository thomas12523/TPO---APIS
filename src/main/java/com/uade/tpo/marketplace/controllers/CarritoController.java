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

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.dto.CarritoRequest;
import com.uade.tpo.marketplace.exceptions.CarritoDuplicateException;
import com.uade.tpo.marketplace.service.CarritoService;

@RestController
@RequestMapping("Carrito")
public class CarritoController {
    private CarritoService carritoService;

    public CarritoController() {
        carritoService = new CarritoService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Carrito>> getCarritos() {
        return ResponseEntity.ok(carritoService.getCarritos());
    }

    @GetMapping("{carritoId}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable int carritoId) {
        Optional<Carrito> result = carritoService.getCarritoById(carritoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearCarrito(@RequestBody CarritoRequest carritoRequest) throws CarritoDuplicateException {
        Carrito carrito = Carrito.builder()
                .carritoId(carritoRequest.getCarritoId())
                .usuarioId(carritoRequest.getUsuarioId())
                .fechaCarrito(carritoRequest.getFechaCarrito())
                .build();
        Carrito result = carritoService.crearCarrito(carrito);
        return ResponseEntity.created(URI.create("/Carrito/" + result.getCarritoId())).body(result);
    }

    @PutMapping("{carritoId}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable int carritoId, @RequestBody Carrito entity) {
        Carrito result = carritoService.actualizarCarrito(carritoId, entity);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{carritoId}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable int carritoId) {
        boolean deleted = carritoService.deleteCarrito(carritoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
