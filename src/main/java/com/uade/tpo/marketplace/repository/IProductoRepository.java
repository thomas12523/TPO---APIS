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

    @Query("select p from Producto p where "
            + "(:categoriaId is null or p.categoria.id = :categoriaId) and "
            + "(:nombre is null or lower(p.nombreProducto) like lower(concat('%', :nombre, '%'))) and "
            + "(:precioMin is null or p.precioUnitario >= :precioMin) and "
            + "(:precioMax is null or p.precioUnitario <= :precioMax)")
    Page<Producto> buscarProductos(Integer categoriaId, String nombre, Double precioMin, Double precioMax, Pageable pageable);
}
