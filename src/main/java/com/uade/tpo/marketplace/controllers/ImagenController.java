package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.dto.ImagenRequest;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;
import com.uade.tpo.marketplace.service.imagen.ImagenService;

@RestController
@RequestMapping("Imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<Imagen>> getImagenes(@RequestParam(required = false) Integer productoId) {
        return ResponseEntity.ok(imagenService.getImagenes(productoId));
    }

    @GetMapping("{imagenId}")
    public ResponseEntity<Imagen> getImagenById(@PathVariable int imagenId) {
        Optional<Imagen> result = imagenService.getImagenById(imagenId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearImagen(@RequestBody ImagenRequest imagenRequest) throws ImagenDuplicateException {
        Imagen result = imagenService.crearImagen(imagenRequest);
        return ResponseEntity.created(URI.create("/Imagen/" + result.getImagenId())).body(result);
    }

    @PutMapping("{imagenId}")
    public ResponseEntity<Imagen> actualizarImagen(@PathVariable int imagenId, @RequestBody ImagenRequest imagenRequest) {
        Imagen result = imagenService.actualizarImagen(imagenId, imagenRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{imagenId}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int imagenId) {
        boolean deleted = imagenService.deleteImagen(imagenId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
