package com.uade.tpo.marketplace.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.dto.request.ImagenRequest;
import com.uade.tpo.marketplace.entity.dto.response.ImagenResponse;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;
import com.uade.tpo.marketplace.service.imagen.IImagenService;

@RestController
@RequestMapping("Imagen")
public class ImagenController {

    @Autowired
    private IImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<ImagenResponse>> getImagenes(@RequestParam(required = false) Integer productoId) {
        return ResponseEntity.ok(imagenService.getImagenes(productoId).stream()
                .map(ImagenResponse::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("{imagenId}")
    public ResponseEntity<ImagenResponse> getImagenById(@PathVariable int imagenId) {
        Optional<Imagen> result = imagenService.getImagenById(imagenId);
        if (result.isPresent())
            return ResponseEntity.ok(ImagenResponse.from(result.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearImagen(@RequestBody ImagenRequest imagenRequest) throws ImagenDuplicateException {
        Imagen result = imagenService.crearImagen(imagenRequest);
        return ResponseEntity.ok(ImagenResponse.from(result));
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> subirImagen(@RequestParam("productoId") int productoId,
            @RequestParam("image") MultipartFile image) throws IOException {
        Imagen result = imagenService.subirImagen(productoId, image);
        return ResponseEntity.ok(ImagenResponse.from(result));
    }

    @PutMapping("{imagenId}")
    public ResponseEntity<ImagenResponse> actualizarImagen(@PathVariable int imagenId, @RequestBody ImagenRequest imagenRequest) {
        Imagen result = imagenService.actualizarImagen(imagenId, imagenRequest);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ImagenResponse.from(result));
    }

    @DeleteMapping("{imagenId}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int imagenId) {
        boolean deleted = imagenService.deleteImagen(imagenId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
