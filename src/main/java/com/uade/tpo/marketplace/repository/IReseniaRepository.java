package com.uade.tpo.marketplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Resenia;

@Repository
public interface IReseniaRepository extends JpaRepository<Resenia, Integer> {

    @Query(value = "select r from Resenia r where r.usuario.usuarioId = ?1 and r.producto.productoId = ?2")
    Optional<Resenia> findByUsuarioIdAndProductoId(int usuarioId, int productoId);

    @Query(value = "select r from Resenia r where r.producto.productoId = ?1")
    Page<Resenia> findByProductoId(int productoId, Pageable pageable);
}
