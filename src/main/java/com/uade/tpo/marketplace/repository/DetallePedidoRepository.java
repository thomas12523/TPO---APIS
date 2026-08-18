package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetallePedido;

public class DetallePedidoRepository {
    private ArrayList<DetallePedido> detallesPedido;

    public DetallePedidoRepository() {
        detallesPedido = new ArrayList<DetallePedido>();
    }

    public ArrayList<DetallePedido> getDetallesPedido() {
        return this.detallesPedido;
    }

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return this.detallesPedido.stream().filter(d -> d.getDetallePedidoId() == detallePedidoId).findAny();
    }

    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        this.detallesPedido.add(detallePedido);
        return detallePedido;
    }

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedido detallePedidoActualizado) {
        Optional<DetallePedido> detallePedido = getDetallePedidoById(detallePedidoId);
        if (detallePedido.isEmpty()) {
            return null;
        }
        this.detallesPedido.remove(detallePedido.get());
        this.detallesPedido.add(detallePedidoActualizado);
        return detallePedidoActualizado;
    }

    public boolean deleteDetallePedido(int detallePedidoId) {
        Optional<DetallePedido> detallePedido = getDetallePedidoById(detallePedidoId);
        if (detallePedido.isEmpty()) {
            return false;
        }
        return this.detallesPedido.remove(detallePedido.get());
    }
}
