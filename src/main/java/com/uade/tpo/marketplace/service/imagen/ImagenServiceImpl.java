package com.uade.tpo.marketplace.service.imagen;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.request.ImagenRequest;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.repository.IImagenRepository;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private IProductoService productoService;

    @Transactional(readOnly = true)
    public List<Imagen> getImagenes(Integer productoId) {
        if (productoId != null)
            return imagenRepository.findByProductoId(productoId);

        return imagenRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Imagen> getImagenById(int imagenId) {
        return imagenRepository.findById(imagenId);
    }

    public Imagen crearImagen(ImagenRequest imagenRequest) throws ImagenDuplicateException {
        if (imagenRepository.findByProductoIdAndImagenUrl(
                imagenRequest.getProductoId(), imagenRequest.getImagenUrl()).isPresent())
            throw new ImagenDuplicateException();

        Producto producto = productoService.getProductoById(imagenRequest.getProductoId())
                .orElseThrow(ProductoNotFoundException::new);

        Imagen imagen = new Imagen();
        imagen.setProducto(producto);
        imagen.setImagenUrl(imagenRequest.getImagenUrl());
        return imagenRepository.save(imagen);
    }

    public Imagen subirImagen(int productoId, MultipartFile archivo) throws IOException {
        Producto producto = productoService.getProductoById(productoId)
                .orElseThrow(ProductoNotFoundException::new);

        Imagen imagen = new Imagen();
        imagen.setProducto(producto);
        imagen.setDatos(archivo.getBytes());
        imagen.setTipoContenido(archivo.getContentType());
        return imagenRepository.save(imagen);
    }

    public Imagen actualizarImagen(int imagenId, ImagenRequest imagenRequest) {
        Optional<Imagen> existente = imagenRepository.findById(imagenId);
        if (existente.isEmpty())
            return null;

        Imagen imagen = existente.get();
        imagen.setImagenUrl(imagenRequest.getImagenUrl());
        return imagenRepository.save(imagen);
    }

    public Optional<Imagen> deleteImagen(int imagenId) {
        Optional<Imagen> existente = imagenRepository.findById(imagenId);
        if (existente.isEmpty())
            return Optional.empty();

        Imagen imagen = existente.get();
        imagen.setActivo(false);
        return Optional.of(imagenRepository.save(imagen));
    }
}
