package com.uade.tpo.marketplace.service.carrito;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.dto.CarritoRequest;

public interface ICarritoService {

    public List<Carrito> getCarritos();

    public Optional<Carrito> getCarritoById(int carritoId);

    public Carrito crearCarrito(CarritoRequest carritoRequest);

    public Carrito actualizarCarrito(int carritoId, CarritoRequest carritoRequest);

    public boolean deleteCarrito(int carritoId);
}
