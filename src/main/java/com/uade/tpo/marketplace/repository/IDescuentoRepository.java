package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Descuento;

@Repository
public interface IDescuentoRepository extends JpaRepository<Descuento, Integer> {

    @Query(value = "select d from Descuento d where d.producto.productoId = ?1")
    List<Descuento> findByProductoId(int productoId);
}
