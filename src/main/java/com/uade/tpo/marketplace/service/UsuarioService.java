package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.repository.UsuarioRepository;

public class UsuarioService {

    public ArrayList<Usuario> getUsuarios() {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        return usuarioRepository.getUsuarios();
    }

    public Usuario getUsuarioById(int usuarioId) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        return usuarioRepository.getUsuarioById(usuarioId);
    }

    public Usuario crearUsuario(Usuario usuario) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        return usuarioRepository.crearUsuario(usuario);
    }

    public Usuario actualizarUsuario(int usuarioId, Usuario usuario) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        return usuarioRepository.actualizarUsuario(usuarioId, usuario);
    }

    public boolean deleteUsuario(int usuarioId) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        return usuarioRepository.deleteUsuario(usuarioId);
    }
}
