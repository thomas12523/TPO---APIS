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

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.service.ReseniaService;

@RestController
@RequestMapping("Resenia")
public class ReseniaController {

    @GetMapping
    public ArrayList<Resenia> getResenias() {
        ReseniaService reseniaService = new ReseniaService();
        return reseniaService.getResenias();
    }

    @GetMapping("{reseniaId}")
    public Resenia getReseniaById(@PathVariable int reseniaId) {
        ReseniaService reseniaService = new ReseniaService();
        return reseniaService.getReseniaById(reseniaId);
    }

    @PostMapping
    public Resenia crearResenia(@RequestBody Resenia entity) {
        ReseniaService reseniaService = new ReseniaService();
        return reseniaService.crearResenia(entity);
    }

    @PutMapping("{reseniaId}")
    public Resenia actualizarResenia(@PathVariable int reseniaId, @RequestBody Resenia entity) {
        ReseniaService reseniaService = new ReseniaService();
        return reseniaService.actualizarResenia(reseniaId, entity);
    }

    @DeleteMapping("{reseniaId}")
    public boolean deleteResenia(@PathVariable int reseniaId) {
        ReseniaService reseniaService = new ReseniaService();
        return reseniaService.deleteResenia(reseniaId);
    }
}
