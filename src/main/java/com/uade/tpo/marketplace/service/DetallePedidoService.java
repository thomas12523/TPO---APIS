package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.repository.DetallePedidoRepository;

public class DetallePedidoService {
    private DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService() {
        detallePedidoRepository = new DetallePedidoRepository();
    }

    public ArrayList<DetallePedido> getDetallesPedido() {
        return detallePedidoRepository.getDetallesPedido();
    }

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return detallePedidoRepository.getDetallePedidoById(detallePedidoId);
    }

    public DetallePedido crearDetallePedido(DetallePedido detallePedido) throws DetallePedidoDuplicateException {
        if (detallePedidoRepository.getDetallePedidoById(detallePedido.getDetallePedidoId()).isPresent())
            throw new DetallePedidoDuplicateException();
        return detallePedidoRepository.crearDetallePedido(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedido detallePedido) {
        return detallePedidoRepository.actualizarDetallePedido(detallePedidoId, detallePedido);
    }

    public boolean deleteDetallePedido(int detallePedidoId) {
        return detallePedidoRepository.deleteDetallePedido(detallePedidoId);
    }
}
