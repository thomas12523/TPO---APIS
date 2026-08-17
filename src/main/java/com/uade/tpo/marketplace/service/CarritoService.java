package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.repository.CarritoRepository;

public class CarritoService {

    public ArrayList<Carrito> getCarritos() {
        CarritoRepository carritoRepository = new CarritoRepository();
        return carritoRepository.getCarritos();
    }

    public Carrito getCarritoById(int carritoId) {
        CarritoRepository carritoRepository = new CarritoRepository();
        return carritoRepository.getCarritoById(carritoId);
    }

    public Carrito crearCarrito(Carrito carrito) {
        CarritoRepository carritoRepository = new CarritoRepository();
        return carritoRepository.crearCarrito(carrito);
    }

    public Carrito actualizarCarrito(int carritoId, Carrito carrito) {
        CarritoRepository carritoRepository = new CarritoRepository();
        return carritoRepository.actualizarCarrito(carritoId, carrito);
    }

    public boolean deleteCarrito(int carritoId) {
        CarritoRepository carritoRepository = new CarritoRepository();
        return carritoRepository.deleteCarrito(carritoId);
    }
}
