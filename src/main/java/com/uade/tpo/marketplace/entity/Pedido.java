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
public class Pedido {

    public Pedido() {
    }

    public Pedido(int pedidoId, Usuario usuario, String fechaCreacion, String estado, double subtotal, double total,
            String metodoPago) {
        this.pedidoId = pedidoId;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.subtotal = subtotal;
        this.total = total;
        this.metodoPago = metodoPago;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column
    private String fechaCreacion;

    @Column
    private String estado;

    @Column
    private double subtotal;

    @Column
    private double total;

    @Column
    private String metodoPago;
}
