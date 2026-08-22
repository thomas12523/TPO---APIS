package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.DetalleCarrito;

@Repository
public interface IDetalleCarritoRepository extends JpaRepository<DetalleCarrito, Integer> {

    @Query(value = "select dc from DetalleCarrito dc where dc.carrito.carritoId = ?1 and dc.producto.productoId = ?2")
    Optional<DetalleCarrito> findByCarritoIdAndProductoId(int carritoId, int productoId);

    @Query(value = "select dc from DetalleCarrito dc where dc.carrito.carritoId = ?1")
    List<DetalleCarrito> findByCarritoId(int carritoId);
}
