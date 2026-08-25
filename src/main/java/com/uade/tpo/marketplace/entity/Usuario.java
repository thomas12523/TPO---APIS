package com.uade.tpo.marketplace.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

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
    private List<Pedido> pedidos;
}
