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
    private boolean activo;

    public ProductoResponse(Producto producto) {
        this.productoId = producto.getProductoId();
        this.categoriaId = producto.getCategoria().getId();
        this.categoriaNombre = producto.getCategoria().getNombre();
        this.nombreProducto = producto.getNombreProducto();
        this.descripcion = producto.getDescripcion();
        this.precioUnitario = producto.getPrecioUnitario();
        this.stock = producto.getStock();
        this.imagenUrl = producto.getImagenUrl();
        this.activo = producto.isActivo();
    }
}
