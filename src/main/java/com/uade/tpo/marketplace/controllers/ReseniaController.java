package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.dto.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.service.ReseniaService;

@RestController
@RequestMapping("Resenia")
public class ReseniaController {
    private ReseniaService reseniaService;

    public ReseniaController() {
        reseniaService = new ReseniaService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Resenia>> getResenias() {
        return ResponseEntity.ok(reseniaService.getResenias());
    }

    @GetMapping("{reseniaId}")
    public ResponseEntity<Resenia> getReseniaById(@PathVariable int reseniaId) {
        Optional<Resenia> result = reseniaService.getReseniaById(reseniaId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearResenia(@RequestBody ReseniaRequest reseniaRequest) throws ReseniaDuplicateException {
        Resenia resenia = Resenia.builder()
                .reseniaId(reseniaRequest.getReseniaId())
                .usuarioId(reseniaRequest.getUsuarioId())
                .productoId(reseniaRequest.getProductoId())
                .calificacion(reseniaRequest.getCalificacion())
                .comentario(reseniaRequest.getComentario())
                .fechaCreacion(reseniaRequest.getFechaCreacion())
                .build();
        Resenia result = reseniaService.crearResenia(resenia);
        return ResponseEntity.created(URI.create("/Resenia/" + result.getReseniaId())).body(result);
    }

    @PutMapping("{reseniaId}")
    public ResponseEntity<Resenia> actualizarResenia(@PathVariable int reseniaId, @RequestBody Resenia entity) {
        Resenia result = reseniaService.actualizarResenia(reseniaId, entity);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{reseniaId}")
    public ResponseEntity<Void> deleteResenia(@PathVariable int reseniaId) {
        boolean deleted = reseniaService.deleteResenia(reseniaId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
