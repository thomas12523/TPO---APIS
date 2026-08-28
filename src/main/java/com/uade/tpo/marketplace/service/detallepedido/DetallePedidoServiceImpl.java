package com.uade.tpo.marketplace.service.detallepedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.DetallePedidoRequest;
import com.uade.tpo.marketplace.exceptions.DetallePedidoDuplicateException;
import com.uade.tpo.marketplace.repository.IDetallePedidoRepository;
import com.uade.tpo.marketplace.service.descuento.IDescuentoService;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class DetallePedidoServiceImpl implements IDetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDescuentoService descuentoService;

    @Transactional(readOnly = true)
    public List<DetallePedido> getDetallesPedido(Integer pedidoId) {
        if (pedidoId != null)
            return detallePedidoRepository.findByPedidoId(pedidoId);

        return detallePedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId);
    }

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        if (detallePedidoRepository.findByPedidoIdAndProductoId(
                detallePedidoRequest.getPedidoId(), detallePedidoRequest.getProductoId()).isPresent())
            throw new DetallePedidoDuplicateException();

        Pedido pedido = pedidoService.getPedidoById(detallePedidoRequest.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        Producto producto = productoService.getProductoById(detallePedidoRequest.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        double precioConDescuento = descuentoService.getPrecioConDescuento(producto);
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(precioConDescuento);
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getCantidad() * precioConDescuento);
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedidoRequest detallePedidoRequest) {
        Optional<DetallePedido> existente = detallePedidoRepository.findById(detallePedidoId);
        if (existente.isEmpty())
            return null;

        DetallePedido detallePedido = existente.get();
        double precioConDescuento = descuentoService.getPrecioConDescuento(detallePedido.getProducto());
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(precioConDescuento);
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getCantidad() * precioConDescuento);
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
