package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Resenia;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Integer> {

    Optional<Resenia> findByUsuario_UsuarioIdAndProducto_ProductoId(int usuarioId, int productoId);

    List<Resenia> findByProducto_ProductoId(int productoId);
}
