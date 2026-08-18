package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Usuario;

public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<Usuario>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public Optional<Usuario> getUsuarioById(int usuarioId) {
        return this.usuarios.stream().filter(u -> u.getUsuarioId() == usuarioId).findAny();
    }

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return this.usuarios.stream().filter(u -> u.getEmail().equals(email)).findAny();
    }

    public Usuario crearUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        return usuario;
    }

    public Usuario actualizarUsuario(int usuarioId, Usuario usuarioActualizado) {
        Optional<Usuario> usuario = getUsuarioById(usuarioId);
        if (usuario.isEmpty()) {
            return null;
        }
        this.usuarios.remove(usuario.get());
        this.usuarios.add(usuarioActualizado);
        return usuarioActualizado;
    }

    public boolean deleteUsuario(int usuarioId) {
        Optional<Usuario> usuario = getUsuarioById(usuarioId);
        if (usuario.isEmpty()) {
            return false;
        }
        return this.usuarios.remove(usuario.get());
    }
}
