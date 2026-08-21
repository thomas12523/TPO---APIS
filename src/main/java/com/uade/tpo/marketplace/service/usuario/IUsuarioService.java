package com.uade.tpo.marketplace.service.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;

public interface IUsuarioService {

    public Page<Usuario> getUsuarios(PageRequest pageRequest);

    public Optional<Usuario> getUsuarioById(int usuarioId);

    public Optional<Usuario> login(String email, String contrasenia);

    public Usuario crearUsuario(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException;

    public Usuario actualizarUsuario(int usuarioId, UsuarioRequest usuarioRequest);

    public boolean deleteUsuario(int usuarioId);
}
