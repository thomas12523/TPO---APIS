package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Integer> {

    @Query(value = "select i from Imagen i where i.producto.productoId = ?1 and i.imagenUrl = ?2")
    Optional<Imagen> findByProductoIdAndImagenUrl(int productoId, String imagenUrl);

    @Query(value = "select i from Imagen i where i.producto.productoId = ?1")
    List<Imagen> findByProductoId(int productoId);
}
