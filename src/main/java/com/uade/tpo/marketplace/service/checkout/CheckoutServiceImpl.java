package com.uade.tpo.marketplace.service.checkout;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.DetallePedido;
import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.entity.Pedido;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.CheckoutRequest;
import com.uade.tpo.marketplace.exceptions.CarritoVacioException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.repository.ICarritoRepository;
import com.uade.tpo.marketplace.repository.IDetalleCarritoRepository;
import com.uade.tpo.marketplace.repository.IDetallePedidoRepository;
import com.uade.tpo.marketplace.repository.IEnvioRepository;
import com.uade.tpo.marketplace.repository.IPedidoRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IDetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IEnvioRepository envioRepository;

    public Pedido checkout(int carritoId, CheckoutRequest checkoutRequest) throws CarritoVacioException, StockInsuficienteException {
        Optional<Carrito> carritoOpt = carritoRepository.findById(carritoId);
        if (carritoOpt.isEmpty()) {
            throw new EntityNotFoundException("Carrito no encontrado");
        }
        Carrito carrito = carritoOpt.get();

        List<DetalleCarrito> items = detalleCarritoRepository.findByCarrito_CarritoId(carritoId);
        if (items.isEmpty())
            throw new CarritoVacioException();

        for (DetalleCarrito item : items) {
            if (item.getCantidad() > item.getProducto().getStock())
                throw new StockInsuficienteException();
        }

        double subtotal = items.stream()
                .mapToDouble(item -> item.getCantidad() * item.getPrecioUnitario())
                .sum();

        Pedido pedido = new Pedido();
        pedido.setUsuario(carrito.getUsuario());
        pedido.setFechaCreacion(LocalDate.now().toString());
        pedido.setEstado("PENDIENTE");
        pedido.setSubtotal(subtotal);
        pedido.setTotal(subtotal + checkoutRequest.getCostoEnvio());
        pedido.setMetodoPago(checkoutRequest.getMetodoPago());
        pedido = pedidoRepository.save(pedido);

        for (DetalleCarrito item : items) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(item.getProducto());
            detallePedido.setCantidad(item.getCantidad());
            detallePedido.setPrecioUnitario(item.getPrecioUnitario());
            detallePedido.setSubtotal(item.getCantidad() * item.getPrecioUnitario());
            detallePedidoRepository.save(detallePedido);

            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }

        Envio envio = new Envio();
        envio.setPedido(pedido);
        envio.setDireccion(checkoutRequest.getDireccion());
        envio.setMetodoEnvio(checkoutRequest.getMetodoEnvio());
        envio.setCostoEnvio(checkoutRequest.getCostoEnvio());
        envioRepository.save(envio);

        detalleCarritoRepository.deleteAll(items);

        return pedido;
    }
}
