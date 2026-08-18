package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.exceptions.CarritoDuplicateException;
import com.uade.tpo.marketplace.repository.CarritoRepository;

public class CarritoService {
    private CarritoRepository carritoRepository;

    public CarritoService() {
        carritoRepository = new CarritoRepository();
    }

    public ArrayList<Carrito> getCarritos() {
        return carritoRepository.getCarritos();
    }

    public Optional<Carrito> getCarritoById(int carritoId) {
        return carritoRepository.getCarritoById(carritoId);
    }

    public Carrito crearCarrito(Carrito carrito) throws CarritoDuplicateException {
        if (carritoRepository.getCarritoById(carrito.getCarritoId()).isPresent())
            throw new CarritoDuplicateException();
        return carritoRepository.crearCarrito(carrito);
    }

    public Carrito actualizarCarrito(int carritoId, Carrito carrito) {
        return carritoRepository.actualizarCarrito(carritoId, carrito);
    }

    public boolean deleteCarrito(int carritoId) {
        return carritoRepository.deleteCarrito(carritoId);
    }
}
