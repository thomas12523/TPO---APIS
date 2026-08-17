package com.uade.tpo.marketplace.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.service.DetalleCarritoService;

@RestController
@RequestMapping("DetalleCarrito")
public class DetalleCarritoController {

    @GetMapping
    public ArrayList<DetalleCarrito> getDetallesCarrito() {
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        return detalleCarritoService.getDetallesCarrito();
    }

    @GetMapping("{carritoId}/{productoId}")
    public DetalleCarrito getDetalleCarritoById(@PathVariable int carritoId, @PathVariable int productoId) {
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        return detalleCarritoService.getDetalleCarritoById(carritoId, productoId);
    }

    @PostMapping
    public DetalleCarrito crearDetalleCarrito(@RequestBody DetalleCarrito entity) {
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        return detalleCarritoService.crearDetalleCarrito(entity);
    }

    @PutMapping("{carritoId}/{productoId}")
    public DetalleCarrito actualizarDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId, @RequestBody DetalleCarrito entity) {
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        return detalleCarritoService.actualizarDetalleCarrito(carritoId, productoId, entity);
    }

    @DeleteMapping("{carritoId}/{productoId}")
    public boolean deleteDetalleCarrito(@PathVariable int carritoId, @PathVariable int productoId) {
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        return detalleCarritoService.deleteDetalleCarrito(carritoId, productoId);
    }
}
