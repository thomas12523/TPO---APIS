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
import com.uade.tpo.marketplace.repository.DetallePedidoRepository;
import com.uade.tpo.marketplace.repository.PedidoRepository;
import com.uade.tpo.marketplace.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<DetallePedido> getDetallesPedido() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> getDetallePedidoById(int detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId);
    }

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) throws DetallePedidoDuplicateException {
        if (detallePedidoRepository.findByPedido_PedidoIdAndProducto_ProductoId(
                detallePedidoRequest.getPedidoId(), detallePedidoRequest.getProductoId()).isPresent())
            throw new DetallePedidoDuplicateException();

        Pedido pedido = pedidoRepository.findById(detallePedidoRequest.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        Producto producto = productoRepository.findById(detallePedidoRequest.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(detallePedidoRequest.getPrecioUnitario());
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getSubtotal());
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(int detallePedidoId, DetallePedidoRequest detallePedidoRequest) {
        Optional<DetallePedido> existente = detallePedidoRepository.findById(detallePedidoId);
        if (existente.isEmpty())
            return null;

        DetallePedido detallePedido = existente.get();
        detallePedido.setCantidad(detallePedidoRequest.getCantidad());
        detallePedido.setPrecioUnitario(detallePedidoRequest.getPrecioUnitario());
        detallePedido.setObservaciones(detallePedidoRequest.getObservaciones());
        detallePedido.setSubtotal(detallePedidoRequest.getSubtotal());
        return detallePedidoRepository.save(detallePedido);
    }

    public boolean deleteDetallePedido(int detallePedidoId) {
        if (detallePedidoRepository.findById(detallePedidoId).isEmpty())
            return false;

        detallePedidoRepository.deleteById(detallePedidoId);
        return true;
    }
}
