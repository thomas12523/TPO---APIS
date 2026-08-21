package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.DetallePedido;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    Optional<DetallePedido> findByPedido_PedidoIdAndProducto_ProductoId(int pedidoId, int productoId);

    List<DetallePedido> findByPedido_PedidoId(int pedidoId);
}
