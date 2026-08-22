package com.uade.tpo.marketplace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query(value = "select p from Pedido p where p.usuario.usuarioId = ?1")
    Page<Pedido> findByUsuarioId(int usuarioId, Pageable pageable);
}
