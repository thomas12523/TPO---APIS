package com.uade.tpo.marketplace.service.resena;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Resena;
import com.uade.tpo.marketplace.entity.Role;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.ResenaRequest;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.exceptions.ResenaDuplicateException;
import com.uade.tpo.marketplace.exceptions.ResenaInvalidaException;
import com.uade.tpo.marketplace.repository.IResenaRepository;
import com.uade.tpo.marketplace.service.producto.IProductoService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ResenaServiceImpl implements IResenaService {

    @Autowired
    private IResenaRepository resenaRepository;

    @Autowired
    private IProductoService productoService;

    @Transactional
    public List<Resena> getResenas(Integer productoId) {
        if (productoId != null)
            return resenaRepository.findByProductoId(productoId);

        return resenaRepository.findAll();
    }

    @Transactional
    public Optional<Resena> getResenaById(int resenaId) {
        return resenaRepository.findById(resenaId);
    }

    public Resena crearResena(ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException, ResenaDuplicateException, ProductoNotFoundException {
        validarPuntuacion(resenaRequest.getPuntuacion());

        Producto producto = productoService.getProductoById(resenaRequest.getProductoId())
                .orElseThrow(ProductoNotFoundException::new);

        if (resenaRepository.findByProductoIdAndUsuarioId(producto.getProductoId(), usuarioActual.getUsuarioId()).isPresent())
            throw new ResenaDuplicateException();

        Resena resena = new Resena();
        resena.setProducto(producto);
        resena.setUsuario(usuarioActual);
        resena.setPuntuacion(resenaRequest.getPuntuacion());
        resena.setComentario(resenaRequest.getComentario());
        resena.setFecha(LocalDate.now().toString());
        resena.setActivo(true);
        return resenaRepository.save(resena);
    }

    public Optional<Resena> actualizarResena(int resenaId, ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException {
        Optional<Resena> existente = resenaRepository.findById(resenaId);
        if (existente.isEmpty())
            return Optional.empty();

        Resena resena = existente.get();
        validarPropietario(resena, usuarioActual);
        validarPuntuacion(resenaRequest.getPuntuacion());

        resena.setPuntuacion(resenaRequest.getPuntuacion());
        resena.setComentario(resenaRequest.getComentario());
        return Optional.of(resenaRepository.save(resena));
    }

    public Optional<Resena> deleteResena(int resenaId, Usuario usuarioActual) {
        Optional<Resena> existente = resenaRepository.findById(resenaId);
        if (existente.isEmpty())
            return Optional.empty();

        Resena resena = existente.get();
        validarPropietario(resena, usuarioActual);
        resena.setActivo(false);
        return Optional.of(resenaRepository.save(resena));
    }

    private void validarPropietario(Resena resena, Usuario usuarioActual) {
        if (resena.getUsuario().getUsuarioId() != usuarioActual.getUsuarioId() && usuarioActual.getRole() != Role.ADMIN)
            throw new AccessDeniedException("No podes modificar la resena de otro usuario");
    }

    private void validarPuntuacion(int puntuacion) throws ResenaInvalidaException {
        if (puntuacion < 1 || puntuacion > 5)
            throw new ResenaInvalidaException();
    }
}
