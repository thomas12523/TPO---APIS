package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.DetallePedido;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    @Query(value = "select dp from DetallePedido dp where dp.pedido.pedidoId = ?1 and dp.producto.productoId = ?2 and dp.activo = true")
    Optional<DetallePedido> findByPedidoIdAndProductoId(int pedidoId, int productoId);

    @Query(value = "select dp from DetallePedido dp where dp.pedido.pedidoId = ?1 and dp.activo = true")
    List<DetallePedido> findByPedidoId(int pedidoId);

    List<DetallePedido> findByActivoTrue();
}
