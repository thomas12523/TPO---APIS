package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.ProductoRequest;
import com.uade.tpo.marketplace.entity.dto.request.StockRequest;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.ProductoResponse;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.exceptions.StockInvalidoException;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@RestController
@RequestMapping("Producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<Page<ProductoResponse>> getProductos(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(productoService.getProductos(categoriaId, nombre, precioMin, precioMax, PageRequest.of(page, size)).map(ProductoResponse::from));
    }

    @GetMapping("{productoId}")
    public ResponseEntity<ProductoResponse> getProductoById(@PathVariable int productoId) {
        Optional<Producto> result = productoService.getProductoById(productoId);
        if (result.isPresent())
            return ResponseEntity.ok(ProductoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearProducto(@RequestBody ProductoRequest productoRequest) throws ProductoDuplicateException {
        Producto result = productoService.crearProducto(productoRequest);
        return ResponseEntity.ok(ProductoResponse.from(result));
    }

    @PutMapping("{productoId}")
    public ResponseEntity<ProductoResponse> actualizarProducto(@PathVariable int productoId, @RequestBody ProductoRequest productoRequest) {
        Optional<Producto> result = productoService.actualizarProducto(productoId, productoRequest);
        if (result.isPresent())
            return ResponseEntity.ok(ProductoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{productoId}")
    public ResponseEntity<Object> deleteProducto(@PathVariable int productoId) {
        Optional<Producto> result = productoService.deleteProducto(productoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Producto desactivado correctamente", ProductoResponse.from(result.get())));
    }

    @PatchMapping("{productoId}/stock")
    public ResponseEntity<ProductoResponse> actualizarStock(@PathVariable int productoId, @RequestBody StockRequest stockRequest) throws StockInvalidoException {
        Optional<Producto> result = productoService.actualizarStock(productoId, stockRequest.getStock());
        if (result.isPresent())
            return ResponseEntity.ok(ProductoResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }
}
