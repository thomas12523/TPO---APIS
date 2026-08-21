package com.uade.tpo.marketplace.service.detallepedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.DetallePedidoRequest;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.repository.IDetallePedidoRepository;
import com.uade.tpo.marketplace.repository.IPedidoRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public List<DetallePedido> getDetallesPedido(Integer pedidoId) {
        if (pedidoId != null)
            return detallePedidoRepository.findByPedido_PedidoId(pedidoId);

        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId);
    }

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        if (detallePedidoRepository.findByPedido_PedidoIdAndProducto_ProductoId(
                detallePedidoRequest.getPedidoId(), detallePedidoRequest.getProductoId()).isPresent())
            throw new DetallePedidoDuplicateException();

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(detallePedidoRequest.getPedidoId());
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("Pedido no encontrado");
        }
        Pedido pedido = pedidoOpt.get();

        Optional<Producto> productoOpt = productoRepository.findById(detallePedidoRequest.getProductoId());
        if (productoOpt.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        Producto producto = productoOpt.get();

        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(producto.getPrecioConDescuento());
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getCantidad() * producto.getPrecioConDescuento());
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedidoRequest detallePedidoRequest) {
        Optional<DetallePedido> existente = detallePedidoRepository.findById(detallePedidoId);
        if (existente.isEmpty())
            return null;

        DetallePedido detallePedido = existente.get();
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(detallePedido.getProducto().getPrecioConDescuento());
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getCantidad() * detallePedido.getProducto().getPrecioConDescuento());
        return detallePedidoRepository.save(detallePedido);
    }

    public boolean deleteDetallePedido(int detallePedidoId) {
        Optional<DetallePedido> existente = detallePedidoRepository.findById(detallePedidoId);
        if (existente.isEmpty())
            return false;

        DetallePedido detallePedido = existente.get();
        Producto producto = detallePedido.getProducto();
        producto.setStock(producto.getStock() + detallePedido.getCantidad());
        productoRepository.save(producto);

        detallePedidoRepository.deleteById(detallePedidoId);
        return true;
    }
}
