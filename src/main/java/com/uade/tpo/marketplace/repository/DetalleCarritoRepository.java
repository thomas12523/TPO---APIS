package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.DetalleCarrito;

public class DetalleCarritoRepository {
    public static ArrayList<DetalleCarrito> detallesCarrito = new ArrayList<>();

    public ArrayList<DetalleCarrito> getDetallesCarrito() {
        return this.detallesCarrito;
    }

    public DetalleCarrito getDetalleCarritoById(int carritoId, int productoId) {
        for (DetalleCarrito detalleCarrito : this.detallesCarrito) {
            if (detalleCarrito.getCarritoId() == carritoId && detalleCarrito.getProductoId() == productoId) {
                return detalleCarrito;
            }
        }
        return null;
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarrito detalleCarrito) {
        this.detallesCarrito.add(detalleCarrito);
        return detalleCarrito;
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarrito detalleCarritoActualizado) {
        DetalleCarrito detalleCarrito = getDetalleCarritoById(carritoId, productoId);
        if (detalleCarrito == null) {
            return null;
        }
        this.detallesCarrito.remove(detalleCarrito);
        this.detallesCarrito.add(detalleCarritoActualizado);
        return detalleCarritoActualizado;
    }

    public boolean deleteDetalleCarrito(int carritoId, int productoId) {
        DetalleCarrito detalleCarrito = getDetalleCarritoById(carritoId, productoId);
        if (detalleCarrito == null) {
            return false;
        }
        return this.detallesCarrito.remove(detalleCarrito);
    }
}
