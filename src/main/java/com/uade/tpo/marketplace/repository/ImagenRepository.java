package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Imagen;

public class ImagenRepository {
    private ArrayList<Imagen> imagenes;

    public ImagenRepository() {
        imagenes = new ArrayList<Imagen>();
    }

    public ArrayList<Imagen> getImagenes() {
        return this.imagenes;
    }

    public Optional<Imagen> getImagenById(int imagenId) {
        return this.imagenes.stream().filter(i -> i.getImagenId() == imagenId).findAny();
    }

    public Imagen crearImagen(Imagen imagen) {
        this.imagenes.add(imagen);
        return imagen;
    }

    public Imagen actualizarImagen(int imagenId, Imagen imagenActualizada) {
        Optional<Imagen> imagen = getImagenById(imagenId);
        if (imagen.isEmpty()) {
            return null;
        }
        this.imagenes.remove(imagen.get());
        this.imagenes.add(imagenActualizada);
        return imagenActualizada;
    }

    public boolean deleteImagen(int imagenId) {
        Optional<Imagen> imagen = getImagenById(imagenId);
        if (imagen.isEmpty()) {
            return false;
        }
        return this.imagenes.remove(imagen.get());
    }
}
