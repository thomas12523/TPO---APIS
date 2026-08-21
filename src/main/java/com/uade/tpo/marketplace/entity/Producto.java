package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Producto {

    public Producto() {
    }

    public Producto(int productoId, Category categoria, String nombreProducto, String descripcion,
            double precioUnitario, int stock, String imagenUrl, double descuentoPorcentaje) {
        this.productoId = productoId;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productoId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category categoria;

    @Column
    private String nombreProducto;

    @Column
    private String descripcion;

    @Column
    private double precioUnitario;

    @Column
    private int stock;

    @Column
    private String imagenUrl;

    @Column
    private double descuentoPorcentaje;

    @Transient
    public double getPrecioConDescuento() {
        return precioUnitario - (precioUnitario * descuentoPorcentaje / 100);
    }
}
