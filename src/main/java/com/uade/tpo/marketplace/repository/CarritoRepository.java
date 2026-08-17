package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Carrito;

public class CarritoRepository {
    public ArrayList<Carrito> carritos = new ArrayList<>();

    public ArrayList<Carrito> getCarritos() {
        return this.carritos;
    }

    public Carrito getCarritoById(int carritoId) {
        for (Carrito carrito : this.carritos) {
            if (carrito.getCarritoId() == carritoId) {
                return carrito;
            }
        }
        return null;
    }

    public Carrito crearCarrito(Carrito carrito) {
        this.carritos.add(carrito);
        return carrito;
    }

    public Carrito actualizarCarrito(int carritoId, Carrito carritoActualizado) {
        Carrito carrito = getCarritoById(carritoId);
        if (carrito == null) {
            return null;
        }
        this.carritos.remove(carrito);
        this.carritos.add(carritoActualizado);
        return carritoActualizado;
    }

    public boolean deleteCarrito(int carritoId) {
        Carrito carrito = getCarritoById(carritoId);
        if (carrito == null) {
            return false;
        }
        return this.carritos.remove(carrito);
    }
}
