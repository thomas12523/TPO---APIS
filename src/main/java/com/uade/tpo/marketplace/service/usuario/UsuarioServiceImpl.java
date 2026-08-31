package com.uade.tpo.marketplace.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.repository.IUsuarioRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<Usuario> getUsuarios(PageRequest pageRequest) {
        return usuarioRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioById(int usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    public Usuario crearUsuario(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException {
        if (usuarioRepository.findByEmail(usuarioRequest.getEmail()).isPresent())
            throw new UsuarioDuplicateException();

        Usuario usuario = new Usuario();
        usuario.setDni(usuarioRequest.getDni());
        usuario.setUsername(usuarioRequest.getUsername());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setApellido(usuarioRequest.getApellido());
        usuario.setContrasenia(passwordEncoder.encode(usuarioRequest.getContrasenia()));
        usuario.setRole(Role.USER);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> actualizarUsuario(int usuarioId, UsuarioRequest usuarioRequest) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            usuario.setDni(usuarioRequest.getDni());
            usuario.setUsername(usuarioRequest.getUsername());
            usuario.setEmail(usuarioRequest.getEmail());
            usuario.setNombre(usuarioRequest.getNombre());
            usuario.setApellido(usuarioRequest.getApellido());
            usuario.setContrasenia(passwordEncoder.encode(usuarioRequest.getContrasenia()));
            return usuarioRepository.save(usuario);
        });
    }

    public Optional<Usuario> actualizarPermisos(int usuarioId, Role role) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            usuario.setRole(role);
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deleteUsuario(int usuarioId) {
        if (usuarioRepository.findById(usuarioId).isEmpty())
            return false;

        usuarioRepository.deleteById(usuarioId);
        return true;
    }
}
