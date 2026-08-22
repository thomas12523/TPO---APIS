package com.uade.tpo.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Envio;

@Repository
public interface IEnvioRepository extends JpaRepository<Envio, Integer> {

    @Query(value = "select e from Envio e where e.pedido.pedidoId = ?1")
    Optional<Envio> findByPedidoId(int pedidoId);
}
