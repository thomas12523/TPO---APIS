package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import com.uade.tpo.marketplace.entity.ImagenEntity;

public class ImagenService {

    // luego se deberia conectar con imagerepository

    public ArrayList<ImagenEntity> getImagenes() {
        return new ArrayList<>();
    }

    public ImagenEntity getImagenById(int imagenId) {
        return null;
    }

    public ImagenEntity crearImagen(ImagenEntity imagen) {
        return imagen;
    }

    public ImagenEntity actualizarImagen(int imagenId, ImagenEntity imagen) {
        return imagen;
    }

    public boolean deleteImagen(int imagenId) {
        return false;
    }
}