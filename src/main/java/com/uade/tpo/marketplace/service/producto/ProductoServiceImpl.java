package com.uade.tpo.marketplace.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.repository.CategoriesRepository;
import com.uade.tpo.marketplace.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
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
