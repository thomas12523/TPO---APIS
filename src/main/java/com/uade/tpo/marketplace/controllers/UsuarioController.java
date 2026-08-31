package com.uade.tpo.marketplace.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.UsuarioRequest;
import com.uade.tpo.marketplace.entity.dto.response.UsuarioResponse;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

@RestController
@RequestMapping("Usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> getUsuarios(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(usuarioService.getUsuarios(PageRequest.of(0, Integer.MAX_VALUE)).map(UsuarioResponse::from));
        return ResponseEntity.ok(usuarioService.getUsuarios(PageRequest.of(page, size)).map(UsuarioResponse::from));
    }

    @GetMapping("{usuarioId}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable int usuarioId) {
        Optional<Usuario> result = usuarioService.getUsuarioById(usuarioId);
        if (result.isPresent())
            return ResponseEntity.ok(UsuarioResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioRequest usuarioRequest) throws UsuarioDuplicateException {
        Usuario result = usuarioService.crearUsuario(usuarioRequest);
        return ResponseEntity.ok(UsuarioResponse.from(result));
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable int usuarioId, @RequestBody UsuarioRequest usuarioRequest) {
        Optional<Usuario> result = usuarioService.actualizarUsuario(usuarioId, usuarioRequest);
        if (result.isPresent())
            return ResponseEntity.ok(UsuarioResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{usuarioId}/permisos") // actualizo los permisos de un usuario (USER o ADMIN)
    public ResponseEntity<UsuarioResponse> actualizarPermisos(@PathVariable int usuarioId, @RequestParam Role role) {
        Optional<Usuario> result = usuarioService.actualizarPermisos(usuarioId, role);
        if (result.isPresent())
            return ResponseEntity.ok(UsuarioResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int usuarioId) {
        boolean deleted = usuarioService.deleteUsuario(usuarioId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
