package com.uade.tpo.marketplace.service.detallecarrito;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.dto.DetalleCarritoRequest;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;

public interface DetalleCarritoService {

    public List<DetalleCarrito> getDetallesCarrito();

    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId);

    public DetalleCarrito crearDetalleCarrito(DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException;

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarritoRequest detalleCarritoRequest);

    public boolean deleteDetalleCarrito(int carritoId, int productoId);
}
