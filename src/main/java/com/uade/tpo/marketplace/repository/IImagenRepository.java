package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Integer> {

    // ordenadas por id para que la primera sea la principal; las filas viejas sin archivo no se devuelven
    @Query(value = "select i from Imagen i where i.producto.productoId = ?1 and i.activo = true and i.datos is not null order by i.imagenId")
    List<Imagen> findByProductoId(int productoId);

    List<Imagen> findByActivoTrue();
}
