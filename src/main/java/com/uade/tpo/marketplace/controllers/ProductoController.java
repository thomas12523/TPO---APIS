package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@RestController
@RequestMapping("Producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<Page<Producto>> getProductos(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productoService.getProductos(categoriaId, nombre, precioMin, precioMax, PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productoService.getProductos(categoriaId, nombre, precioMin, precioMax, PageRequest.of(page, size)));
    }

    @GetMapping("{productoId}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int productoId) {
        Optional<Producto> result = productoService.getProductoById(productoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearProducto(@RequestBody ProductoRequest productoRequest) throws ProductoDuplicateException {
        Producto result = productoService.crearProducto(productoRequest);
        return ResponseEntity.created(URI.create("/Producto/" + result.getProductoId())).body(result);
    }

    @PutMapping("{productoId}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int productoId, @RequestBody ProductoRequest productoRequest) {
        Producto result = productoService.actualizarProducto(productoId, productoRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{productoId}")
    public ResponseEntity<Void> deleteProducto(@PathVariable int productoId) {
        boolean deleted = productoService.deleteProducto(productoId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
