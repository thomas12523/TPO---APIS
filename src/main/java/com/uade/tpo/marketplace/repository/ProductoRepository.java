package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "select p from Producto p where p.nombreProducto = ?1")
    List<Producto> findByNombreProducto(String nombreProducto);
}
