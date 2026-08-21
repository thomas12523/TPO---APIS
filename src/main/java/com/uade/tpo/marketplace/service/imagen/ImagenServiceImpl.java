package com.uade.tpo.marketplace.service.imagen;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.dto.ImagenRequest;
import com.uade.tpo.marketplace.exceptions.ImagenDuplicateException;
import com.uade.tpo.marketplace.repository.IImagenRepository;
import com.uade.tpo.marketplace.repository.IProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public List<Imagen> getImagenes(Integer productoId) {
        if (productoId != null)
            return imagenRepository.findByProducto_ProductoId(productoId);

        return imagenRepository.findAll();
    }

    public Optional<Imagen> getImagenById(int imagenId) {
        return imagenRepository.findById(imagenId);
    }

    public Imagen crearImagen(ImagenRequest imagenRequest) throws ImagenDuplicateException {
        if (imagenRepository.findByProducto_ProductoIdAndImagenUrl(
                imagenRequest.getProductoId(), imagenRequest.getImagenUrl()).isPresent())
            throw new ImagenDuplicateException();

        Optional<Producto> productoOpt = productoRepository.findById(imagenRequest.getProductoId());
        if (productoOpt.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        Producto producto = productoOpt.get();

        Imagen imagen = new Imagen();
        imagen.setProducto(producto);
        imagen.setImagenUrl(imagenRequest.getImagenUrl());
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

    public boolean deleteImagen(int imagenId) {
        if (imagenRepository.findById(imagenId).isEmpty())
            return false;

        imagenRepository.deleteById(imagenId);
        return true;
    }
}
