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
import com.uade.tpo.marketplace.service.producto.IProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IProductoService productoService;

    public List<DetallePedido> getDetallesPedido(Integer pedidoId) {
        if (pedidoId != null)
            return detallePedidoRepository.findByPedidoId(pedidoId);

        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId);
    }

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        if (detallePedidoRepository.findByPedidoIdAndProductoId(
                detallePedidoRequest.getPedidoId(), detallePedidoRequest.getProductoId()).isPresent())
            throw new DetallePedidoDuplicateException();

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(detallePedidoRequest.getPedidoId());
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("Pedido no encontrado");
        }
        Pedido pedido = pedidoOpt.get();

        Optional<Producto> productoOpt = productoService.getProductoById(detallePedidoRequest.getProductoId());
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
        productoService.ajustarStock(detallePedido.getProducto().getProductoId(), detallePedido.getCantidad());

        detallePedidoRepository.deleteById(detallePedidoId);
        return true;
    }

    public DetallePedido guardarDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void deleteDetallesByPedidoId(int pedidoId) {
        detallePedidoRepository.deleteAll(detallePedidoRepository.findByPedidoId(pedidoId));
    }
}
