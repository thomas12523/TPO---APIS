package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;
import com.uade.tpo.marketplace.repository.ImagenRepository;

public class ImagenService {
    private ImagenRepository imagenRepository;

    public ImagenService() {
        imagenRepository = new ImagenRepository();
    }

    public ArrayList<Imagen> getImagenes() {
        return imagenRepository.getImagenes();
    }

    public Optional<Imagen> getImagenById(int imagenId) {
        return imagenRepository.getImagenById(imagenId);
    }

    public Imagen crearImagen(Imagen imagen) throws ImagenDuplicateException {
        if (imagenRepository.getImagenById(imagen.getImagenId()).isPresent())
            throw new ImagenDuplicateException();
        return imagenRepository.crearImagen(imagen);
    }

    public Imagen actualizarImagen(int imagenId, Imagen imagen) {
        return imagenRepository.actualizarImagen(imagenId, imagen);
    }

    public boolean deleteImagen(int imagenId) {
        return imagenRepository.deleteImagen(imagenId);
    }
}
