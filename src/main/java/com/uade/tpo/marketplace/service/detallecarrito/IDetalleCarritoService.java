package com.uade.tpo.marketplace.service.detallecarrito;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.dto.request.DetalleCarritoRequest;
import com.uade.tpo.marketplace.exceptions.AdminNoPuedeComprarException;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.exceptions.ProductoNoDisponibleException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;

public interface IDetalleCarritoService {

    public List<DetalleCarrito> getDetallesCarrito(Integer carritoId);

    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId);

    public DetalleCarrito crearDetalleCarrito(DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException, StockInsuficienteException, AdminNoPuedeComprarException, ProductoNoDisponibleException;

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarritoRequest detalleCarritoRequest) throws StockInsuficienteException, AdminNoPuedeComprarException, ProductoNoDisponibleException;

    public Optional<DetalleCarrito> deleteDetalleCarrito(int carritoId, int productoId);

    public void deleteDetallesByCarritoId(int carritoId);
}
