package com.uade.tpo.marketplace.service.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;

public interface IProductoService {

    public Page<Producto> getProductos(Integer categoriaId, String nombre, Double precioMin, Double precioMax, PageRequest pageRequest);

    public Optional<Producto> getProductoById(int productoId);

    public Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicateException;

    public Optional<Producto> actualizarProducto(int productoId, ProductoRequest productoRequest);

    public boolean deleteProducto(int productoId);

    public Producto ajustarStock(int productoId, int delta);
}
