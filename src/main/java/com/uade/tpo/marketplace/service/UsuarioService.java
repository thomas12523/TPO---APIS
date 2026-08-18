package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarioRepository.getUsuarios();
    }

    public Optional<Usuario> getUsuarioById(int usuarioId) {
        return usuarioRepository.getUsuarioById(usuarioId);
    }

    public Optional<Usuario> login(String email, String contrasenia) {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioByEmail(email);
        if (usuario.isEmpty() || !usuario.get().getContrasenia().equals(contrasenia)) {
            return Optional.empty();
        }
        return usuario;
    }

    public Usuario crearUsuario(Usuario usuario) throws UsuarioDuplicateException {
        if (usuarioRepository.getUsuarioById(usuario.getUsuarioId()).isPresent())
            throw new UsuarioDuplicateException();
        return usuarioRepository.crearUsuario(usuario);
    }

    public Usuario actualizarUsuario(int usuarioId, Usuario usuario) {
        return usuarioRepository.actualizarUsuario(usuarioId, usuario);
    }

    public boolean deleteUsuario(int usuarioId) {
        return usuarioRepository.deleteUsuario(usuarioId);
    }
}
