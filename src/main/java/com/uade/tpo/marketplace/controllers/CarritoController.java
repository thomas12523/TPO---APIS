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

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.service.CarritoService;

@RestController
@RequestMapping("Carrito")
public class CarritoController {

    @GetMapping
    public ArrayList<Carrito> getCarritos() {
        CarritoService carritoService = new CarritoService();
        return carritoService.getCarritos();
    }

    @GetMapping("{carritoId}")
    public Carrito getCarritoById(@PathVariable int carritoId) {
        CarritoService carritoService = new CarritoService();
        return carritoService.getCarritoById(carritoId);
    }

    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito entity) {
        CarritoService carritoService = new CarritoService();
        return carritoService.crearCarrito(entity);
    }

    @PutMapping("{carritoId}")
    public Carrito actualizarCarrito(@PathVariable int carritoId, @RequestBody Carrito entity) {
        CarritoService carritoService = new CarritoService();
        return carritoService.actualizarCarrito(carritoId, entity);
    }

    @DeleteMapping("{carritoId}")
    public boolean deleteCarrito(@PathVariable int carritoId) {
        CarritoService carritoService = new CarritoService();
        return carritoService.deleteCarrito(carritoId);
    }
}
