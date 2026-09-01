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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @Column(unique = true)
    private String numeroPedido;

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

    @Column(nullable = false)
    private boolean activo = true;
}
