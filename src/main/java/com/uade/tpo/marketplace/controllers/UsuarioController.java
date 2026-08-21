package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

@RestController
@RequestMapping("Usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<Usuario>> getUsuarios(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(usuarioService.getUsuarios(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(usuarioService.getUsuarios(PageRequest.of(page, size)));
    }

    @GetMapping("{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int usuarioId) {
        Optional<Usuario> result = usuarioService.getUsuarioById(usuarioId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String contrasenia) {
        Optional<Usuario> result = usuarioService.login(email, contrasenia);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.status(401).build();
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioRequest usuarioRequest) throws UsuarioDuplicateException {
        Usuario result = usuarioService.crearUsuario(usuarioRequest);
        return ResponseEntity.created(URI.create("/Usuario/" + result.getUsuarioId())).body(result);
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int usuarioId, @RequestBody UsuarioRequest usuarioRequest) {
        Usuario result = usuarioService.actualizarUsuario(usuarioId, usuarioRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int usuarioId) {
        boolean deleted = usuarioService.deleteUsuario(usuarioId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
