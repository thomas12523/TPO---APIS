package com.uade.tpo.marketplace.service.detallepedido;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.dto.DetallePedidoRequest;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;

public interface DetallePedidoService {

    public List<DetallePedido> getDetallesPedido();

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId);

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException;

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedidoRequest detallePedidoRequest);

    public boolean deleteDetallePedido(int detallePedidoId);
}
