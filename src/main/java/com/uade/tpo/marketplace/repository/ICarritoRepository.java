package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Carrito;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {

    @Query(value = "select c from Carrito c where c.usuario.usuarioId = ?1")
    List<Carrito> findByUsuarioId(int usuarioId);
}
