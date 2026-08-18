package com.uade.tpo.marketplace.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.service.UsuarioService;

@RestController
@RequestMapping("Usuario")
public class UsuarioController {

    @GetMapping
    public ArrayList<Usuario> getUsuarios() {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.getUsuarios();
    }

    @GetMapping("{usuarioId}")
    public Usuario getUsuarioById(@PathVariable int usuarioId) {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.getUsuarioById(usuarioId);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario entity) {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.crearUsuario(entity);
    }

    @PutMapping("{usuarioId}")
    public Usuario actualizarUsuario(@PathVariable int usuarioId, @RequestBody Usuario entity) {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.actualizarUsuario(usuarioId, entity);
    }

    @DeleteMapping("{usuarioId}")
    public boolean deleteUsuario(@PathVariable int usuarioId) {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.deleteUsuario(usuarioId);
    }
}
