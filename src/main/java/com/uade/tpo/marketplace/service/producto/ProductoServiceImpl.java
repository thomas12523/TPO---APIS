package com.uade.tpo.marketplace.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.repository.ICategoriesRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ICategoriesRepository categoriesRepository;

    public Page<Producto> getProductos(Integer categoriaId, String nombre, PageRequest pageRequest) {
        if (categoriaId != null && nombre != null)
            return productoRepository.findByCategoria_IdAndNombreProductoContainingIgnoreCase(categoriaId, nombre, pageRequest);
        if (categoriaId != null)
            return productoRepository.findByCategoria_Id(categoriaId, pageRequest);
        if (nombre != null)
            return productoRepository.findByNombreProductoContainingIgnoreCase(nombre, pageRequest);

        return productoRepository.findAll(pageRequest);
    }

    public Optional<Producto> getProductoById(int productoId) {
        return productoRepository.findById(productoId);
    }

    public Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicateException {
        if (!productoRepository.findByNombreProducto(productoRequest.getNombreProducto()).isEmpty())
            throw new ProductoDuplicateException();

        Category categoria = categoriesRepository.findById(productoRequest.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        Producto producto = new Producto();
        producto.setCategoria(categoria);
        producto.setNombreProducto(productoRequest.getNombreProducto());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecioUnitario(productoRequest.getPrecioUnitario());
        producto.setStock(productoRequest.getStock());
        producto.setImagenUrl(productoRequest.getImagenUrl());
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(int productoId, ProductoRequest productoRequest) {
        Optional<Producto> existente = productoRepository.findById(productoId);
        if (existente.isEmpty())
            return null;

        Category categoria = categoriesRepository.findById(productoRequest.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

        Producto producto = existente.get();
        producto.setCategoria(categoria);
        producto.setNombreProducto(productoRequest.getNombreProducto());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecioUnitario(productoRequest.getPrecioUnitario());
        producto.setStock(productoRequest.getStock());
        producto.setImagenUrl(productoRequest.getImagenUrl());
        return productoRepository.save(producto);
    }

    public boolean deleteProducto(int productoId) {
        if (productoRepository.findById(productoId).isEmpty())
            return false;

        productoRepository.deleteById(productoId);
        return true;
    }
}
