package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.repository.ProductoRepository;

public class ProductoService {

    public ArrayList<Producto> getProductos() {
        ProductoRepository productoRepository = new ProductoRepository();
        return productoRepository.getProductos();
    }

    public Producto getProductoById(int productoId) {
        ProductoRepository productoRepository = new ProductoRepository();
        return productoRepository.getProductoById(productoId);
    }

    public Producto crearProducto(Producto producto) {
        ProductoRepository productoRepository = new ProductoRepository();
        return productoRepository.crearProducto(producto);
    }

    public Producto actualizarProducto(int productoId, Producto producto) {
        ProductoRepository productoRepository = new ProductoRepository();
        return productoRepository.actualizarProducto(productoId, producto);
    }

    public boolean deleteProducto(int productoId) {
        ProductoRepository productoRepository = new ProductoRepository();
        return productoRepository.deleteProducto(productoId);
    }
}
