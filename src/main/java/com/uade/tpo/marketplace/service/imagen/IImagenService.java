package com.uade.tpo.marketplace.service.imagen;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.dto.request.ImagenRequest;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;

public interface IImagenService {

    public List<Imagen> getImagenes(Integer productoId);

    public Optional<Imagen> getImagenById(int imagenId);

    public Imagen crearImagen(ImagenRequest imagenRequest) throws ImagenDuplicateException;

    public Imagen subirImagen(int productoId, MultipartFile archivo) throws IOException;

    public Imagen actualizarImagen(int imagenId, ImagenRequest imagenRequest);

    public boolean deleteImagen(int imagenId);
}
