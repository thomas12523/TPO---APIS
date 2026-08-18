package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Usuario;

public class UsuarioRepository {
    public ArrayList<Usuario> usuarios = new ArrayList<>();

    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public Usuario getUsuarioById(int usuarioId) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getUsuarioId() == usuarioId) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario crearUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        return usuario;
    }

    public Usuario actualizarUsuario(int usuarioId, Usuario usuarioActualizado) {
        Usuario usuario = getUsuarioById(usuarioId);
        if (usuario == null) {
            return null;
        }
        this.usuarios.remove(usuario);
        this.usuarios.add(usuarioActualizado);
        return usuarioActualizado;
    }

    public boolean deleteUsuario(int usuarioId) {
        Usuario usuario = getUsuarioById(usuarioId);
        if (usuario == null) {
            return false;
        }
        return this.usuarios.remove(usuario);
    }
}
