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

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.service.ProductoService;

@RestController
@RequestMapping("Producto")
public class ProductoController {

    @GetMapping
    public ArrayList<Producto> getProductos() {
        ProductoService productoService = new ProductoService();
        return productoService.getProductos();
    }

    @GetMapping("{productoId}")
    public Producto getProductoById(@PathVariable int productoId) {
        ProductoService productoService = new ProductoService();
        return productoService.getProductoById(productoId);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto entity) {
        ProductoService productoService = new ProductoService();
        return productoService.crearProducto(entity);
    }

    @PutMapping("{productoId}")
    public Producto actualizarProducto(@PathVariable int productoId, @RequestBody Producto entity) {
        ProductoService productoService = new ProductoService();
        return productoService.actualizarProducto(productoId, entity);
    }

    @DeleteMapping("{productoId}")
    public boolean deleteProducto(@PathVariable int productoId) {
        ProductoService productoService = new ProductoService();
        return productoService.deleteProducto(productoId);
    }
}
