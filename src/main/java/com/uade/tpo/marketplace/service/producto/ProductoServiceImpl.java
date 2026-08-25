package com.uade.tpo.marketplace.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Category;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.ProductoRequest;
import com.uade.tpo.marketplace.exceptions.ProductoDuplicateException;
import com.uade.tpo.marketplace.repository.IProductoRepository;
import com.uade.tpo.marketplace.service.categories.ICategoriesService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ICategoriesService categoriesService;

    public Page<Producto> getProductos(Integer categoriaId, String nombre, Double precioMin, Double precioMax, PageRequest pageRequest) {
        return productoRepository.buscarProductos(categoriaId, nombre, precioMin, precioMax, pageRequest);
    }

    public Optional<Producto> getProductoById(int productoId) {
        return productoRepository.findById(productoId);
    }

    public Producto crearProducto(ProductoRequest productoRequest) throws ProductoDuplicateException {
        if (!productoRepository.findByNombreProducto(productoRequest.getNombreProducto()).isEmpty())
            throw new ProductoDuplicateException();

        Category categoria = categoriesService.getCategoryById(productoRequest.getCategoriaId())
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

    public Optional<Producto> actualizarProducto(int productoId, ProductoRequest productoRequest) {
        return productoRepository.findById(productoId).map(producto -> {
            Category categoria = categoriesService.getCategoryById(productoRequest.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

            producto.setCategoria(categoria);
            producto.setNombreProducto(productoRequest.getNombreProducto());
            producto.setDescripcion(productoRequest.getDescripcion());
            producto.setPrecioUnitario(productoRequest.getPrecioUnitario());
            producto.setStock(productoRequest.getStock());
            producto.setImagenUrl(productoRequest.getImagenUrl());
            return productoRepository.save(producto);
        });
    }

    public boolean deleteProducto(int productoId) {
        if (productoRepository.findById(productoId).isEmpty())
            return false;

        productoRepository.deleteById(productoId);
        return true;
    }

    public Producto ajustarStock(int productoId, int delta) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        producto.setStock(producto.getStock() + delta);
        return productoRepository.save(producto);
    }
}
