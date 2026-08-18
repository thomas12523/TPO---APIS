package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetalleCarrito;

public class DetalleCarritoRepository {
    private ArrayList<DetalleCarrito> detallesCarrito;

    public DetalleCarritoRepository() {
        detallesCarrito = new ArrayList<DetalleCarrito>();
    }

    public ArrayList<DetalleCarrito> getDetallesCarrito() {
        return this.detallesCarrito;
    }

    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId) {
        return this.detallesCarrito.stream()
                .filter(d -> d.getCarritoId() == carritoId && d.getProductoId() == productoId).findAny();
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarrito detalleCarrito) {
        this.detallesCarrito.add(detalleCarrito);
        return detalleCarrito;
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId,
            DetalleCarrito detalleCarritoActualizado) {
        Optional<DetalleCarrito> detalleCarrito = getDetalleCarritoById(carritoId, productoId);
        if (detalleCarrito.isEmpty()) {
            return null;
        }
        this.detallesCarrito.remove(detalleCarrito.get());
        this.detallesCarrito.add(detalleCarritoActualizado);
        return detalleCarritoActualizado;
    }

    public boolean deleteDetalleCarrito(int carritoId, int productoId) {
        Optional<DetalleCarrito> detalleCarrito = getDetalleCarritoById(carritoId, productoId);
        if (detalleCarrito.isEmpty()) {
            return false;
        }
        return this.detallesCarrito.remove(detalleCarrito.get());
    }
}
