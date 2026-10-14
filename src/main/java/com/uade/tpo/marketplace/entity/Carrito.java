package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carritoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String fechaCarrito;

    @Column(nullable = false)
    private boolean activo;

    @PrePersist
    private void prePersist() {
        this.activo = true;
    }

    public Carrito() {}

    public int getCarritoId() { return carritoId; }
    public void setCarritoId(int carritoId) { this.carritoId = carritoId; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getFechaCarrito() { return fechaCarrito; }
    public void setFechaCarrito(String fechaCarrito) { this.fechaCarrito = fechaCarrito; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
