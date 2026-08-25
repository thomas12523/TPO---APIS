package com.uade.tpo.marketplace.service.soporte;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.dto.request.CheckoutRequest;
import com.uade.tpo.marketplace.entity.dto.request.PedidoRequest;
import com.uade.tpo.marketplace.exceptions.CarritoVacioException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.service.carrito.ICarritoService;
import com.uade.tpo.marketplace.service.detallecarrito.IDetalleCarritoService;
import com.uade.tpo.marketplace.service.detallepedido.IDetallePedidoService;
import com.uade.tpo.marketplace.service.pedido.IPedidoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private IDetalleCarritoService detalleCarritoService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @Autowired
    private IProductoService productoService;

    public Pedido checkout(int carritoId, CheckoutRequest checkoutRequest) throws CarritoVacioException, StockInsuficienteException {
        Carrito carrito = carritoService.getCarritoById(carritoId)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));

        List<DetalleCarrito> items = detalleCarritoService.getDetallesCarrito(carritoId);
        if (items.isEmpty())
            throw new CarritoVacioException();

        for (DetalleCarrito item : items) {
            if (item.getCantidad() > item.getProducto().getStock())
                throw new StockInsuficienteException();
        }

        double subtotal = items.stream()
                .mapToDouble(item -> item.getCantidad() * item.getPrecioUnitario())
                .sum();

        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setUsuarioId(carrito.getUsuario().getUsuarioId());
        pedidoRequest.setFechaCreacion(LocalDate.now().toString());
        pedidoRequest.setEstado("PENDIENTE");
        pedidoRequest.setSubtotal(subtotal);
        pedidoRequest.setTotal(subtotal);
        pedidoRequest.setMetodoPago(checkoutRequest.getMetodoPago());
        Pedido pedido = pedidoService.crearPedido(pedidoRequest);

        for (DetalleCarrito item : items) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(item.getProducto());
            detallePedido.setCantidad(item.getCantidad());
            detallePedido.setPrecioUnitario(item.getPrecioUnitario());
            detallePedido.setSubtotal(item.getCantidad() * item.getPrecioUnitario());
            detallePedidoService.guardarDetallePedido(detallePedido);

            productoService.ajustarStock(item.getProducto().getProductoId(), -item.getCantidad());
        }

        detalleCarritoService.deleteDetallesByCarritoId(carritoId);

        return pedido;
    }
}
