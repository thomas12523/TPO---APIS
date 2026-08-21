package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Carrito;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {

    List<Carrito> findByUsuario_UsuarioId(int usuarioId);
}
