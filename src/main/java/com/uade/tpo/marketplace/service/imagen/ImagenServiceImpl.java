package com.uade.tpo.marketplace.service.imagen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Imagen;
import com.uade.tpo.marketplace.entity.Producto;
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

    @Transactional
    public List<Imagen> getImagenes(Integer productoId) {
        if (productoId != null)
            return imagenRepository.findByProductoId(productoId);

        return imagenRepository.findByActivoTrue();
    }

    @Transactional
    public Optional<Imagen> getImagenById(int imagenId) {
        return imagenRepository.findById(imagenId);
    }

    @Transactional
    public Optional<Imagen> getImagenPrincipal(int productoId) {
        return imagenRepository.findByProductoId(productoId).stream().findFirst();
    }

    public Imagen subirImagen(int productoId, MultipartFile archivo) throws IOException {
        Producto producto = productoService.getProductoById(productoId)
                .orElseThrow(ProductoNotFoundException::new);

        List<Imagen> existentes = imagenRepository.findByProductoId(productoId);

        Imagen imagen = new Imagen();
        imagen.setProducto(producto);
        imagen.setDatos(archivo.getBytes());
        imagen.setTipoContenido(archivo.getContentType());
        imagen.setOrden(existentes.size());
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

    public List<Imagen> actualizarOrden(List<Integer> imagenIds) {
        List<Imagen> actualizadas = new ArrayList<>();
        if (imagenIds == null)
            return actualizadas;

        for (int i = 0; i < imagenIds.size(); i++) {
            Optional<Imagen> existente = imagenRepository.findById(imagenIds.get(i));
            if (existente.isPresent()) {
                Imagen imagen = existente.get();
                imagen.setOrden(i);
                actualizadas.add(imagenRepository.save(imagen));
            }
        }
        return actualizadas;
    }
}
