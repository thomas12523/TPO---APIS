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
    private double precioConDescuento;
    private boolean tieneDescuento;
    private Double porcentajeDescuento;
    private int stock;
    private boolean activo;

    public ProductoResponse(Producto producto, double precioConDescuento) {
        this.productoId = producto.getProductoId();
        this.categoriaId = producto.getCategoria().getId();
        this.categoriaNombre = producto.getCategoria().getNombre();
        this.nombreProducto = producto.getNombreProducto();
        this.descripcion = producto.getDescripcion();
        this.precioUnitario = producto.getPrecioUnitario();
        this.precioConDescuento = precioConDescuento;
        this.tieneDescuento = precioConDescuento < producto.getPrecioUnitario();
        if (this.tieneDescuento && producto.getPrecioUnitario() > 0) {
            this.porcentajeDescuento = (double) Math.round((1 - precioConDescuento / producto.getPrecioUnitario()) * 100);
        }
        this.stock = producto.getStock();
        this.activo = producto.isActivo();
    }
}
