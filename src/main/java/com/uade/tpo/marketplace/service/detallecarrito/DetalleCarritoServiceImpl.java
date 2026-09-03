package com.uade.tpo.marketplace.service.detallecarrito;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.DetalleCarritoRequest;
import com.uade.tpo.marketplace.exceptions.CarritoNotFoundException;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.repository.IDetalleCarritoRepository;
import com.uade.tpo.marketplace.service.carrito.ICarritoService;
import com.uade.tpo.marketplace.service.descuento.IDescuentoService;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DetalleCarritoServiceImpl implements IDetalleCarritoService {

    @Autowired
    private IDetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDescuentoService descuentoService;

    @Transactional
    public List<DetalleCarrito> getDetallesCarrito(Integer carritoId) {
        if (carritoId != null)
            return detalleCarritoRepository.findByCarritoId(carritoId);

        return detalleCarritoRepository.findByActivoTrue();
    }

    @Transactional
    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId) {
        return detalleCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException, StockInsuficienteException {
        if (detalleCarritoRepository.findByCarritoIdAndProductoId(
                detalleCarritoRequest.getCarritoId(), detalleCarritoRequest.getProductoId()).isPresent())
            throw new DetalleCarritoDuplicateException();

        Carrito carrito = carritoService.getCarritoById(detalleCarritoRequest.getCarritoId())
                .orElseThrow(CarritoNotFoundException::new);

        Producto producto = productoService.getProductoById(detalleCarritoRequest.getProductoId())
                .orElseThrow(ProductoNotFoundException::new);

        if (detalleCarritoRequest.getCantidad() > producto.getStock())
            throw new StockInsuficienteException();

        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCarrito(carrito);
        detalleCarrito.setProducto(producto);
        detalleCarrito.setCantidad(detalleCarritoRequest.getCantidad());
        detalleCarrito.setPrecioUnitario(descuentoService.getPrecioConDescuento(producto));
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarritoRequest detalleCarritoRequest) throws StockInsuficienteException {
        Optional<DetalleCarrito> existente = detalleCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
        if (existente.isEmpty())
            return null;

        DetalleCarrito detalleCarrito = existente.get();
        if (detalleCarritoRequest.getCantidad() > detalleCarrito.getProducto().getStock())
            throw new StockInsuficienteException();

        detalleCarrito.setCantidad(detalleCarritoRequest.getCantidad());
        detalleCarrito.setPrecioUnitario(descuentoService.getPrecioConDescuento(detalleCarrito.getProducto()));
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public Optional<DetalleCarrito> deleteDetalleCarrito(int carritoId, int productoId) {
        Optional<DetalleCarrito> existente = detalleCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
        if (existente.isEmpty())
            return Optional.empty();

        DetalleCarrito detalleCarrito = existente.get();
        detalleCarrito.setActivo(false);
        return Optional.of(detalleCarritoRepository.save(detalleCarrito));
    }

    public void deleteDetallesByCarritoId(int carritoId) {
        List<DetalleCarrito> items = detalleCarritoRepository.findByCarritoId(carritoId);
        for (DetalleCarrito item : items)
            item.setActivo(false);
        detalleCarritoRepository.saveAll(items);
    }
}
