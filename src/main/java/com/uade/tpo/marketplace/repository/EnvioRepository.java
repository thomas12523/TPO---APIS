package com.uade.tpo.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    Optional<Envio> findByPedido_PedidoId(int pedidoId);
}
