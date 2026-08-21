package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Integer> {

    Optional<Imagen> findByProducto_ProductoIdAndImagenUrl(int productoId, String imagenUrl);

    List<Imagen> findByProducto_ProductoId(int productoId);
}
