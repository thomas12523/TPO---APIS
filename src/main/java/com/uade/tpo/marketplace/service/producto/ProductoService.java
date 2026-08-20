package com.uade.tpo.marketplace.service.producto;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;

public interface ProductoService {

    public List<Producto> getProductos();

    public Optional<Producto> getProductoById(int productoId);

    public Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicateException;

    public Producto actualizarProducto(int productoId, ProductoRequest productoRequest);

    public boolean deleteProducto(int productoId);
}
