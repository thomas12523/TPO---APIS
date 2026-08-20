package com.uade.tpo.marketplace.service.usuario;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;

public interface UsuarioService {

    public List<Usuario> getUsuarios();

    public Optional<Usuario> getUsuarioById(int usuarioId);

    public Optional<Usuario> login(String email, String contrasenia);

    public Usuario crearUsuario(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException;

    public Usuario actualizarUsuario(int usuarioId, UsuarioRequest usuarioRequest);

    public boolean deleteUsuario(int usuarioId);
}
