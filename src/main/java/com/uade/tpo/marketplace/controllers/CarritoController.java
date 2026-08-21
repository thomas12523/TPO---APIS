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

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.CarritoRequest;
import com.uade.tpo.marketplace.entity.dto.CheckoutRequest;
import com.uade.tpo.marketplace.exceptions.CarritoVacioException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.service.carrito.ICarritoService;
import com.uade.tpo.marketplace.service.checkout.ICheckoutService;

@RestController
@RequestMapping("Carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private ICheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<List<Carrito>> getCarritos(@RequestParam(required = false) Integer usuarioId) {
        return ResponseEntity.ok(carritoService.getCarritos(usuarioId));
    }

    @GetMapping("{carritoId}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable int carritoId) {
        Optional<Carrito> result = carritoService.getCarritoById(carritoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearCarrito(@RequestBody CarritoRequest carritoRequest) {
        Carrito result = carritoService.crearCarrito(carritoRequest);
        return ResponseEntity.created(URI.create("/Carrito/" + result.getCarritoId())).body(result);
    }

    @PutMapping("{carritoId}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable int carritoId, @RequestBody CarritoRequest carritoRequest) {
        Carrito result = carritoService.actualizarCarrito(carritoId, carritoRequest);
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

    @PostMapping("{carritoId}/checkout")
    public ResponseEntity<Object> checkout(@PathVariable int carritoId, @RequestBody CheckoutRequest checkoutRequest) throws CarritoVacioException, StockInsuficienteException {
        Pedido result = checkoutService.checkout(carritoId, checkoutRequest);
        return ResponseEntity.created(URI.create("/Pedido/" + result.getPedidoId())).body(result);
    }
}
