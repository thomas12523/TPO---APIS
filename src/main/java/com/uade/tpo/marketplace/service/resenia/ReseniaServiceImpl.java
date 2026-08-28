package com.uade.tpo.marketplace.service.resenia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.repository.IReseniaRepository;
import com.uade.tpo.marketplace.service.producto.IProductoService;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ReseniaServiceImpl implements IReseniaService {

    @Autowired
    private IReseniaRepository reseniaRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

    @Transactional(readOnly = true)
    public Page<Resenia> getResenias(Integer productoId, PageRequest pageRequest) {
        if (productoId != null)
            return reseniaRepository.findByProductoId(productoId, pageRequest);

        return reseniaRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Optional<Resenia> getReseniaById(int reseniaId) {
        return reseniaRepository.findById(reseniaId);
    }

    public Resenia crearResenia(ReseniaRequest reseniaRequest) throws ReseniaDuplicateException {
        if (reseniaRepository.findByUsuarioIdAndProductoId(
                reseniaRequest.getUsuarioId(), reseniaRequest.getProductoId()).isPresent())
            throw new ReseniaDuplicateException();

        Usuario usuario = usuarioService.getUsuarioById(reseniaRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Producto producto = productoService.getProductoById(reseniaRequest.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Resenia resenia = new Resenia();
        resenia.setUsuario(usuario);
        resenia.setProducto(producto);
        resenia.setCalificacion(reseniaRequest.getCalificacion());
        resenia.setComentario(reseniaRequest.getComentario());
        resenia.setFechaCreacion(reseniaRequest.getFechaCreacion());
        return reseniaRepository.save(resenia);
    }

    public Optional<Resenia> actualizarResenia(int reseniaId, ReseniaRequest reseniaRequest) {
        return reseniaRepository.findById(reseniaId).map(resenia -> {
            resenia.setCalificacion(reseniaRequest.getCalificacion());
            resenia.setComentario(reseniaRequest.getComentario());
            resenia.setFechaCreacion(reseniaRequest.getFechaCreacion());
            return reseniaRepository.save(resenia);
        });
    }

    public boolean deleteResenia(int reseniaId) {
        if (reseniaRepository.findById(reseniaId).isEmpty())
            return false;

        reseniaRepository.deleteById(reseniaId);
        return true;
    }
}
