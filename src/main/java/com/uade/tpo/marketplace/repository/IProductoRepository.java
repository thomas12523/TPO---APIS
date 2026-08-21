package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "select p from Producto p where p.nombreProducto = ?1")
    List<Producto> findByNombreProducto(String nombreProducto);

    Page<Producto> findByCategoria_Id(int categoriaId, Pageable pageable);

    Page<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto, Pageable pageable);

    Page<Producto> findByCategoria_IdAndNombreProductoContainingIgnoreCase(int categoriaId, String nombreProducto, Pageable pageable);
}
