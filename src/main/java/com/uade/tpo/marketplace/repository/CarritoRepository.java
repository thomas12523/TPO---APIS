package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Carrito;

public class CarritoRepository {
    private ArrayList<Carrito> carritos;

    public CarritoRepository() {
        carritos = new ArrayList<Carrito>();
    }

    public ArrayList<Carrito> getCarritos() {
        return this.carritos;
    }

    public Optional<Carrito> getCarritoById(int carritoId) {
        return this.carritos.stream().filter(c -> c.getCarritoId() == carritoId).findAny();
    }

    public Carrito crearCarrito(Carrito carrito) {
        this.carritos.add(carrito);
        return carrito;
    }

    public Carrito actualizarCarrito(int carritoId, Carrito carritoActualizado) {
        Optional<Carrito> carrito = getCarritoById(carritoId);
        if (carrito.isEmpty()) {
            return null;
        }
        this.carritos.remove(carrito.get());
        this.carritos.add(carritoActualizado);
        return carritoActualizado;
    }

    public boolean deleteCarrito(int carritoId) {
        Optional<Carrito> carrito = getCarritoById(carritoId);
        if (carrito.isEmpty()) {
            return false;
        }
        return this.carritos.remove(carrito.get());
    }
}
