package com.uade.tpo.marketplace.service.detallepedido;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.dto.request.DetallePedidoRequest;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;

public interface IDetallePedidoService {

    public List<DetallePedido> getDetallesPedido(Integer pedidoId);

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId);

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException, StockInsuficienteException;

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedidoRequest detallePedidoRequest) throws StockInsuficienteException;

    public Optional<DetallePedido> deleteDetallePedido(int detallePedidoId);

    public DetallePedido guardarDetallePedido(DetallePedido detallePedido);
}
