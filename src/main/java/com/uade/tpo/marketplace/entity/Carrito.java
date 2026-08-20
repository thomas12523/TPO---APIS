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
public class Carrito {

    public Carrito() {
    }

    public Carrito(int carritoId, Usuario usuario, String fechaCarrito) {
        this.carritoId = carritoId;
        this.usuario = usuario;
        this.fechaCarrito = fechaCarrito;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carritoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column
    private String fechaCarrito;
}
