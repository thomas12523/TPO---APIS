package com.uade.tpo.marketplace.service.detallecarrito;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.DetalleCarrito;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.DetalleCarritoRequest;
import com.uade.tpo.marketplace.exceptions.DetalleCarritoDuplicateException;
import com.uade.tpo.marketplace.exceptions.StockInsuficienteException;
import com.uade.tpo.marketplace.repository.ICarritoRepository;
import com.uade.tpo.marketplace.repository.IDetalleCarritoRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetalleCarritoServiceImpl implements IDetalleCarritoService {

    @Autowired
    private IDetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public List<DetalleCarrito> getDetallesCarrito() {
        return detalleCarritoRepository.findAll();
    }

    public Optional<DetalleCarrito> getDetalleCarritoById(int carritoId, int productoId) {
        return detalleCarritoRepository.findByCarrito_CarritoIdAndProducto_ProductoId(carritoId, productoId);
    }

    public DetalleCarrito crearDetalleCarrito(DetalleCarritoRequest detalleCarritoRequest) throws DetalleCarritoDuplicateException, StockInsuficienteException {
        if (detalleCarritoRepository.findByCarrito_CarritoIdAndProducto_ProductoId(
                detalleCarritoRequest.getCarritoId(), detalleCarritoRequest.getProductoId()).isPresent())
            throw new DetalleCarritoDuplicateException();

        Carrito carrito = carritoRepository.findById(detalleCarritoRequest.getCarritoId())
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        Producto producto = productoRepository.findById(detalleCarritoRequest.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        if (detalleCarritoRequest.getCantidad() > producto.getStock())
            throw new StockInsuficienteException();

        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCarrito(carrito);
        detalleCarrito.setProducto(producto);
        detalleCarrito.setCantidad(detalleCarritoRequest.getCantidad());
        detalleCarrito.setPrecioUnitario(producto.getPrecioConDescuento());
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public DetalleCarrito actualizarDetalleCarrito(int carritoId, int productoId, DetalleCarritoRequest detalleCarritoRequest) throws StockInsuficienteException {
        Optional<DetalleCarrito> existente = detalleCarritoRepository.findByCarrito_CarritoIdAndProducto_ProductoId(carritoId, productoId);
        if (existente.isEmpty())
            return null;

        DetalleCarrito detalleCarrito = existente.get();
        if (detalleCarritoRequest.getCantidad() > detalleCarrito.getProducto().getStock())
            throw new StockInsuficienteException();

        detalleCarrito.setCantidad(detalleCarritoRequest.getCantidad());
        detalleCarrito.setPrecioUnitario(detalleCarrito.getProducto().getPrecioConDescuento());
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public boolean deleteDetalleCarrito(int carritoId, int productoId) {
        Optional<DetalleCarrito> existente = detalleCarritoRepository.findByCarrito_CarritoIdAndProducto_ProductoId(carritoId, productoId);
        if (existente.isEmpty())
            return false;

        detalleCarritoRepository.deleteById(existente.get().getDetalleCarritoId());
        return true;
    }
}
