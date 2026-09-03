package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Carrito;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {

    @Query(value = "select c from Carrito c where c.usuario.usuarioId = ?1 and c.activo = true")
    List<Carrito> findByUsuarioId(int usuarioId);

    @Query(value = "select c from Carrito c where c.usuario.usuarioId = ?1 and c.activo = true")
    Optional<Carrito> findActivoByUsuarioId(int usuarioId);

    List<Carrito> findByActivoTrue();
}
