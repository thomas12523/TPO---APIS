package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.repository.DetalleCarritoRepository;

public class DetalleCarritoService {
    private DetalleCarritoRepository detalleCarritoRepository;

    public DetalleCarritoService() {
        detalleCarritoRepository = new DetalleCarritoRepository();
    }

    public ArrayList<DetalleCarrito> getDetallesCarrito() {
        return detalleCarritoRepository.getDetallesCarrito();
    }

    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId) {
        return detalleCarritoRepository.getDetalleCarritoById(carritoId, productoId);
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarrito detalleCarrito) throws DetalleCarritoDuplicateException {
        if (detalleCarritoRepository.getDetalleCarritoById(detalleCarrito.getCarritoId(), detalleCarrito.getProductoId()).isPresent())
            throw new DetalleCarritoDuplicateException();
        return detalleCarritoRepository.crearDetalleCarrito(detalleCarrito);
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.actualizarDetalleCarrito(carritoId, productoId, detalleCarrito);
    }

    public boolean deleteDetalleCarrito(int carritoId, int productoId) {
        return detalleCarritoRepository.deleteDetalleCarrito(carritoId, productoId);
    }
}
