package com.uade.tpo.marketplace.service.soporte;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.exceptions.PedidoEstadoInvalidoException;
import com.uade.tpo.marketplace.exceptions.PedidoNoCanceladoException;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PedidoOrchestratorServiceImpl implements IPedidoOrchestratorService {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @Autowired
    private IProductoService productoService;

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

    // a que estados puede pasar un pedido desde cada estado (solo hacia adelante)
    private static final Map<String, List<String>> TRANSICIONES = Map.of(
            "PENDIENTE", List.of("ACEPTADO", "CANCELADO"),
            "ACEPTADO", List.of("ENVIADO", "CANCELADO"),
            "ENVIADO", List.of("ENTREGADO"),
            "ENTREGADO", List.of(),
            "CANCELADO", List.of());

    // el cliente solo puede cancelar mientras el pedido no fue aceptado
    public Pedido cancelarPedidoCliente(int pedidoId) throws PedidoEstadoInvalidoException {
        Optional<Pedido> pedidoOpt = pedidoService.getPedidoById(pedidoId);
        if (pedidoOpt.isEmpty())
            return null;

        if (!"PENDIENTE".equals(pedidoOpt.get().getEstado()))
            throw new PedidoEstadoInvalidoException();

        return cancelarPedido(pedidoId);
    }

    // lo usa el admin; si cancela, se devuelve el stock igual que cuando cancela el cliente
    public Pedido cambiarEstado(int pedidoId, String nuevoEstado) throws PedidoEstadoInvalidoException {
        Optional<Pedido> pedidoOpt = pedidoService.getPedidoById(pedidoId);
        if (pedidoOpt.isEmpty())
            return null;

        List<String> permitidos = TRANSICIONES.getOrDefault(pedidoOpt.get().getEstado(), List.of());
        if (!permitidos.contains(nuevoEstado))
            throw new PedidoEstadoInvalidoException();

        if ("CANCELADO".equals(nuevoEstado))
            return cancelarPedido(pedidoId);

        return pedidoService.actualizarEstado(pedidoId, nuevoEstado);
    }
    
    public Optional<Pedido> eliminarPedido(int pedidoId) throws PedidoNoCanceladoException {
        return pedidoService.deletePedido(pedidoId);
    }
}
