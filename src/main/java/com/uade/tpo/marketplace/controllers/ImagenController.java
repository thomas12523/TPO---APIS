package com.uade.tpo.marketplace.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.dto.response.DeleteResponse;
import com.uade.tpo.marketplace.entity.dto.response.ImagenResponse;
import com.uade.tpo.marketplace.service.imagen.IImagenService;

@RestController
@RequestMapping("Imagen")
public class ImagenController {

    @Autowired
    private IImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<ImagenResponse>> getImagenes(@RequestParam(required = false) Integer productoId) {
        return ResponseEntity.ok(imagenService.getImagenes(productoId).stream()
                .map(ImagenResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("{imagenId}")
    public ResponseEntity<ImagenResponse> getImagenById(@PathVariable int imagenId) {
        Optional<Imagen> result = imagenService.getImagenById(imagenId);
        if (result.isPresent())
            return ResponseEntity.ok(new ImagenResponse(result.get()));

        return ResponseEntity.notFound().build();
    }

    // devuelve el archivo de la primera imagen del producto, para usarlo directo en el src de un <img>
    @GetMapping("principal/{productoId}")
    public ResponseEntity<byte[]> getImagenPrincipal(@PathVariable int productoId) {
        Optional<Imagen> result = imagenService.getImagenPrincipal(productoId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        Imagen imagen = result.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagen.getTipoContenido()))
                .body(imagen.getDatos());
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> subirImagen(@RequestParam("productoId") int productoId,
            @RequestParam("image") MultipartFile image) throws IOException {
        Imagen result = imagenService.subirImagen(productoId, image);
        return ResponseEntity.ok(new ImagenResponse(result));
    }

    @PatchMapping("{imagenId}")
    public ResponseEntity<Object> deleteImagen(@PathVariable int imagenId) {
        Optional<Imagen> result = imagenService.deleteImagen(imagenId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DeleteResponse<>("Imagen desactivada correctamente", new ImagenResponse(result.get())));
    }
}
