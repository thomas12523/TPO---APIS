package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.repository.ProductoRepository;

public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService() {
        productoRepository = new ProductoRepository();
    }

    public ArrayList<Producto> getProductos() {
        return productoRepository.getProductos();
    }

    public Optional<Producto> getProductoById(int productoId) {
        return productoRepository.getProductoById(productoId);
    }

    public Producto crearProducto(Producto producto) throws ProductoDuplicateException {
        if (productoRepository.getProductoById(producto.getProductoId()).isPresent())
            throw new ProductoDuplicateException();
        return productoRepository.crearProducto(producto);
    }

    public Producto actualizarProducto(int productoId, Producto producto) {
        return productoRepository.actualizarProducto(productoId, producto);
    }

    public boolean deleteProducto(int productoId) {
        return productoRepository.deleteProducto(productoId);
    }
}
