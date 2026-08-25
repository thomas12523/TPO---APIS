package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

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
}
