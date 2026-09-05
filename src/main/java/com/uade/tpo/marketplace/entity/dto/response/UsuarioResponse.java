package com.uade.tpo.marketplace.entity.dto.response;

import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
    private int usuarioId;
    private int dni;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private Role role;
    private boolean activo;

    public UsuarioResponse(Usuario usuario) {
        this.usuarioId = usuario.getUsuarioId();
        this.dni = usuario.getDni();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.role = usuario.getRole();
        this.activo = usuario.isActivo();
    }
}
