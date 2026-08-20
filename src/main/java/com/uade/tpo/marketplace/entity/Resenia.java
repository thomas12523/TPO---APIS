package com.uade.tpo.marketplace.entity;

import java.util.Date;

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
public class Resenia {

    public Resenia() {
    }

    public Resenia(int reseniaId, Usuario usuario, Producto producto, int calificacion, String comentario,
            Date fechaCreacion) {
        this.reseniaId = reseniaId;
        this.usuario = usuario;
        this.producto = producto;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaCreacion = fechaCreacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reseniaId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column
    private int calificacion;

    @Column
    private String comentario;

    @Column
    private Date fechaCreacion;
}
