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
public class Envio {

    public Envio() {
    }

    public Envio(int envioId, Pedido pedido, String direccion, String metodoEnvio, double costoEnvio) {
        this.envioId = envioId;
        this.pedido = pedido;
        this.direccion = direccion;
        this.metodoEnvio = metodoEnvio;
        this.costoEnvio = costoEnvio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int envioId;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column
    private String direccion;

    @Column
    private String metodoEnvio;

    @Column
    private double costoEnvio;
}
