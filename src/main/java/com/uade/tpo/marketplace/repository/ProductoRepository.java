package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Producto;

public class ProductoRepository {
    public static ArrayList<Producto> productos = new ArrayList<>();

    public ArrayList<Producto> getProductos() {
        return this.productos;
    }

    public Producto getProductoById(int productoId) {
        for (Producto producto : this.productos) {
            if (producto.getProductoId() == productoId) {
                return producto;
            }
        }
        return null;
    }

    public Producto crearProducto(Producto producto) {
        this.productos.add(producto);
        return producto;
    }

    public Producto actualizarProducto(int productoId, Producto productoActualizado) {
        Producto producto = getProductoById(productoId);
        if (producto == null) {
            return null;
        }
        this.productos.remove(producto);
        this.productos.add(productoActualizado);
        return productoActualizado;
    }

    public boolean deleteProducto(int productoId) {
        Producto producto = getProductoById(productoId);
        if (producto == null) {
            return false;
        }
        return this.productos.remove(producto);
    }
}
