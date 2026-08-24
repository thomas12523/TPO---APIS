package com.uade.tpo.marketplace.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Usuario {

    public Usuario() {
    }

    public Usuario(int usuarioId, int dni, String username, String email, String nombre, String apellido, String contrasenia,
            List<Pedido> pedidos) {
        this.usuarioId = usuarioId;
        this.dni = dni;
        this.username = username;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.pedidos = pedidos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;

    @Column
    private int dni;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String contrasenia;

    @Column
    private boolean admin;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Pedido> pedidos;
}
