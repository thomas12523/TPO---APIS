package com.uade.tpo.marketplace.service.imagen;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;

public interface IImagenService {

    public List<Imagen> getImagenes(Integer productoId);

    public Optional<Imagen> getImagenById(int imagenId);

    public Optional<Imagen> getImagenPrincipal(int productoId);

    public Imagen subirImagen(int productoId, MultipartFile archivo) throws IOException;

    public Optional<Imagen> deleteImagen(int imagenId);
}
