package com.uade.tpo.marketplace.service.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(int usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    public Optional<Usuario> login(String email, String contrasenia) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty() || !usuario.get().getContrasenia().equals(contrasenia))
            return Optional.empty();

        return usuario;
    }

    public Usuario crearUsuario(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException {
        if (usuarioRepository.findByEmail(usuarioRequest.getEmail()).isPresent())
            throw new UsuarioDuplicateException();

        Usuario usuario = Usuario.builder()
                .dni(usuarioRequest.getDni())
                .email(usuarioRequest.getEmail())
                .nombre(usuarioRequest.getNombre())
                .apellido(usuarioRequest.getApellido())
                .contrasenia(usuarioRequest.getContrasenia())
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(int usuarioId, UsuarioRequest usuarioRequest) {
        Optional<Usuario> existente = usuarioRepository.findById(usuarioId);
        if (existente.isEmpty())
            return null;

        Usuario usuario = existente.get();
        usuario.setDni(usuarioRequest.getDni());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setApellido(usuarioRequest.getApellido());
        usuario.setContrasenia(usuarioRequest.getContrasenia());
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUsuario(int usuarioId) {
        if (usuarioRepository.findById(usuarioId).isEmpty())
            return false;

        usuarioRepository.deleteById(usuarioId);
        return true;
    }
}
