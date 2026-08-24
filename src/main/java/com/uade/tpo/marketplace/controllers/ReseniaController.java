package com.uade.tpo.marketplace.controllers;

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

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.dto.request.ReseniaRequest;
import com.uade.tpo.marketplace.entity.dto.response.ReseniaResponse;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.service.resenia.IReseniaService;

@RestController
@RequestMapping("Resenia")
public class ReseniaController {

    @Autowired
    private IReseniaService reseniaService;

    @GetMapping
    public ResponseEntity<Page<ReseniaResponse>> getResenias(
            @RequestParam(required = false) Integer productoId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(reseniaService.getResenias(productoId, PageRequest.of(0, Integer.MAX_VALUE)).map(ReseniaResponse::from));
        return ResponseEntity.ok(reseniaService.getResenias(productoId, PageRequest.of(page, size)).map(ReseniaResponse::from));
    }

    @GetMapping("{reseniaId}")
    public ResponseEntity<ReseniaResponse> getReseniaById(@PathVariable int reseniaId) {
        Optional<Resenia> result = reseniaService.getReseniaById(reseniaId);
        if (result.isPresent())
            return ResponseEntity.ok(ReseniaResponse.from(result.get()));

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearResenia(@RequestBody ReseniaRequest reseniaRequest) throws ReseniaDuplicateException {
        Resenia result = reseniaService.crearResenia(reseniaRequest);
        return ResponseEntity.ok(ReseniaResponse.from(result));
    }

    @PutMapping("{reseniaId}")
    public ResponseEntity<ReseniaResponse> actualizarResenia(@PathVariable int reseniaId, @RequestBody ReseniaRequest reseniaRequest) {
        Optional<Resenia> result = reseniaService.actualizarResenia(reseniaId, reseniaRequest);
        if (result.isPresent())
            return ResponseEntity.ok(ReseniaResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{reseniaId}")
    public ResponseEntity<Void> deleteResenia(@PathVariable int reseniaId) {
        boolean deleted = reseniaService.deleteResenia(reseniaId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
