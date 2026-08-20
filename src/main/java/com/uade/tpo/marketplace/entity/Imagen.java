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
public class Imagen {

    public Imagen() {
    }

    public Imagen(int imagenId, Producto producto, String imagenUrl) {
        this.imagenId = imagenId;
        this.producto = producto;
        this.imagenUrl = imagenUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imagenId;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column
    private String imagenUrl;
}
