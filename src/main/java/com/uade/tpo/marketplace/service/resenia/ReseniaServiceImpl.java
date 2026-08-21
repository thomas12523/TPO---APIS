package com.uade.tpo.marketplace.service.resenia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.repository.IProductoRepository;
import com.uade.tpo.marketplace.repository.IReseniaRepository;
import com.uade.tpo.marketplace.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReseniaServiceImpl implements IReseniaService {

    @Autowired
    private IReseniaRepository reseniaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public Page<Resenia> getResenias(Integer productoId, PageRequest pageRequest) {
        if (productoId != null)
            return reseniaRepository.findByProducto_ProductoId(productoId, pageRequest);

        return reseniaRepository.findAll(pageRequest);
    }

    public Optional<Resenia> getReseniaById(int reseniaId) {
        return reseniaRepository.findById(reseniaId);
    }

    public Resenia crearResenia(ReseniaRequest reseniaRequest) throws ReseniaDuplicateException {
        if (reseniaRepository.findByUsuario_UsuarioIdAndProducto_ProductoId(
                reseniaRequest.getUsuarioId(), reseniaRequest.getProductoId()).isPresent())
            throw new ReseniaDuplicateException();

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(reseniaRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        Optional<Producto> productoOpt = productoRepository.findById(reseniaRequest.getProductoId());
        if (productoOpt.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        Producto producto = productoOpt.get();

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
