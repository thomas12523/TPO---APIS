package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.repository.DetalleCarritoRepository;

public class DetalleCarritoService {

    public ArrayList<DetalleCarrito> getDetallesCarrito() {
        DetalleCarritoRepository detalleCarritoRepository = new DetalleCarritoRepository();
        return detalleCarritoRepository.getDetallesCarrito();
    }

    public DetalleCarrito getDetalleCarritoById(int carritoId, int productoId) {
        DetalleCarritoRepository detalleCarritoRepository = new DetalleCarritoRepository();
        return detalleCarritoRepository.getDetalleCarritoById(carritoId, productoId);
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarrito detalleCarrito) {
        DetalleCarritoRepository detalleCarritoRepository = new DetalleCarritoRepository();
        return detalleCarritoRepository.crearDetalleCarrito(detalleCarrito);
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarrito detalleCarrito) {
        DetalleCarritoRepository detalleCarritoRepository = new DetalleCarritoRepository();
        return detalleCarritoRepository.actualizarDetalleCarrito(carritoId, productoId, detalleCarrito);
    }

    public boolean deleteDetalleCarrito(int carritoId, int productoId) {
        DetalleCarritoRepository detalleCarritoRepository = new DetalleCarritoRepository();
        return detalleCarritoRepository.deleteDetalleCarrito(carritoId, productoId);
    }
}
