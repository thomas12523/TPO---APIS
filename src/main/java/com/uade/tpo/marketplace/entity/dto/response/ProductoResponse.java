package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Producto;

import lombok.Data;

@Data
public class ProductoResponse {
    private int productoId;
    private int categoriaId;
    private String categoriaNombre;
    private String nombreProducto;
    private String descripcion;
    private double precioUnitario;
    private int stock;
    private String imagenUrl;

    public static ProductoResponse from(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.setProductoId(producto.getProductoId());
        response.setCategoriaId(producto.getCategoria().getId());
        response.setCategoriaNombre(producto.getCategoria().getNombre());
        response.setNombreProducto(producto.getNombreProducto());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecioUnitario(producto.getPrecioUnitario());
        response.setStock(producto.getStock());
        response.setImagenUrl(producto.getImagenUrl());
        return response;
    }
}
