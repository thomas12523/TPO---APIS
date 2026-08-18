package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Producto;

public class ProductoRepository {
    private ArrayList<Producto> productos;

    public ProductoRepository() {
        productos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> getProductos() {
        return this.productos;
    }

    public Optional<Producto> getProductoById(int productoId) {
        return this.productos.stream().filter(p -> p.getProductoId() == productoId).findAny();
    }

    public Producto crearProducto(Producto producto) {
        this.productos.add(producto);
        return producto;
    }

    public Producto actualizarProducto(int productoId, Producto productoActualizado) {
        Optional<Producto> producto = getProductoById(productoId);
        if (producto.isEmpty()) {
            return null;
        }
        this.productos.remove(producto.get());
        this.productos.add(productoActualizado);
        return productoActualizado;
    }

    public boolean deleteProducto(int productoId) {
        Optional<Producto> producto = getProductoById(productoId);
        if (producto.isEmpty()) {
            return false;
        }
        return this.productos.remove(producto.get());
    }
}
