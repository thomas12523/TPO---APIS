package com.uade.tpo.marketplace.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

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

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}
