package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Descuento {

    public Descuento() {
    }

    public Descuento(int descuentoId, Producto producto, double porcentaje, boolean activo, String fechaInicio,
            String fechaFin) {
        this.descuentoId = descuentoId;
        this.producto = producto;
        this.porcentaje = porcentaje;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int descuentoId;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column
    private double porcentaje;

    @Column
    private boolean activo;

    @Column
    private String fechaInicio;

    @Column
    private String fechaFin;
}
