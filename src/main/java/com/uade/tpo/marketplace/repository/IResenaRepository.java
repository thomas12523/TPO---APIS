package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Resena;

@Repository
public interface IResenaRepository extends JpaRepository<Resena, Integer> {

    @Query(value = "select r from Resena r where r.producto.productoId = ?1")
    List<Resena> findByProductoId(int productoId);

    @Query(value = "select r from Resena r where r.producto.productoId = ?1 and r.usuario.usuarioId = ?2")
    Optional<Resena> findByProductoIdAndUsuarioId(int productoId, int usuarioId);
}
