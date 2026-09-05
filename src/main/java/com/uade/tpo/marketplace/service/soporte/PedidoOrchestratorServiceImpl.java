package com.uade.tpo.marketplace.service.soporte;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
public class PedidoOrchestratorServiceImpl implements IPedidoOrchestratorService {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @Autowired
    private IProductoService productoService;

    @Transactional
    public Pedido cancelarPedido(int pedidoId) {
        Optional<Pedido> pedidoOpt = pedidoService.getPedidoById(pedidoId);
        if (pedidoOpt.isEmpty())
            return null;

        Pedido pedido = pedidoOpt.get();
        if ("CANCELADO".equals(pedido.getEstado()))
            return pedido;

        List<DetallePedido> items = detallePedidoService.getDetallesPedido(pedidoId);
        for (DetallePedido item : items) {
            productoService.ajustarStock(item.getProducto().getProductoId(), item.getCantidad());
        }

        return pedidoService.cancelarPedido(pedidoId);
    }
    
    @Transactional
    public Optional<Pedido> eliminarPedido(int pedidoId) {
        
        return pedidoService.deletePedido(pedidoId);
    }
}
