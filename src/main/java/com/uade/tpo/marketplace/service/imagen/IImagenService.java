package com.uade.tpo.marketplace.service.imagen;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.dto.ImagenRequest;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;

public interface IImagenService {

    public List<Imagen> getImagenes(Integer productoId);

    public Optional<Imagen> getImagenById(int imagenId);

    public Imagen crearImagen(ImagenRequest imagenRequest) throws ImagenDuplicateException;

    public Imagen actualizarImagen(int imagenId, ImagenRequest imagenRequest);

    public boolean deleteImagen(int imagenId);
}
