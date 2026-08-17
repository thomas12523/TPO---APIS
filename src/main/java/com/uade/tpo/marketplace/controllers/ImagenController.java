package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.ImagenEntity;
import com.uade.tpo.marketplace.service.ImagenService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("Imagen")
public class ImagenController {

    @GetMapping("{idImagen}")
    public ImagenEntity getImagenById(@PathVariable int idImagen) {
        ImagenService imagenService = new ImagenService();
        return imagenService.getImagenById(idImagen);
    }

    @PostMapping
    public ImagenEntity crearImagen(@RequestBody ImagenEntity entity) {
        ImagenService imagenService = new ImagenService();
        return imagenService.crearImagen(entity);
    }

    @PutMapping("{idImagen}")
    public ImagenEntity actualizarImagen(@PathVariable int idImagen, @RequestBody ImagenEntity entity) {
        ImagenService imagenService = new ImagenService();
        return imagenService.actualizarImagen(idImagen, entity);
    }

    @DeleteMapping("{idImagen}")
    public boolean deleteImagen(@PathVariable int idImagen) {
        ImagenService imagenService = new ImagenService();
        return imagenService.deleteImagen(idImagen);
    }
}