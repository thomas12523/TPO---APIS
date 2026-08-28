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

    public static UsuarioResponse from(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setUsuarioId(usuario.getUsuarioId());
        response.setDni(usuario.getDni());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setNombre(usuario.getNombre());
        response.setApellido(usuario.getApellido());
        response.setRole(usuario.getRole());
        return response;
    }
}
