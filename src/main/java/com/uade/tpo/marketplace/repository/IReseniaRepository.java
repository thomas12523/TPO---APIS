package com.uade.tpo.marketplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Resenia;

@Repository
public interface IReseniaRepository extends JpaRepository<Resenia, Integer> {

    Optional<Resenia> findByUsuario_UsuarioIdAndProducto_ProductoId(int usuarioId, int productoId);

    Page<Resenia> findByProducto_ProductoId(int productoId, Pageable pageable);
}
