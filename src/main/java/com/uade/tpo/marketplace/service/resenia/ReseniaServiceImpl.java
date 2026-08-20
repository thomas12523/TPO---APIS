package com.uade.tpo.marketplace.service.resenia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Producto;
import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.repository.ProductoRepository;
import com.uade.tpo.marketplace.repository.ReseniaRepository;
import com.uade.tpo.marketplace.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReseniaServiceImpl implements ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Resenia> getResenias() {
        return reseniaRepository.findAll();
    }

    public Optional<Resenia> getReseniaById(int reseniaId) {
        return reseniaRepository.findById(reseniaId);
    }

    public Resenia crearResenia(ReseniaRequest reseniaRequest) throws ReseniaDuplicateException {
        if (reseniaRepository.findByUsuario_UsuarioIdAndProducto_ProductoId(
                reseniaRequest.getUsuarioId(), reseniaRequest.getProductoId()).isPresent())
            throw new ReseniaDuplicateException();

        Usuario usuario = usuarioRepository.findById(reseniaRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(reseniaRequest.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Resenia resenia = Resenia.builder()
                .usuario(usuario)
                .producto(producto)
                .calificacion(reseniaRequest.getCalificacion())
                .comentario(reseniaRequest.getComentario())
                .fechaCreacion(reseniaRequest.getFechaCreacion())
                .build();
        return reseniaRepository.save(resenia);
    }

    public Resenia actualizarResenia(int reseniaId, ReseniaRequest reseniaRequest) {
        Optional<Resenia> existente = reseniaRepository.findById(reseniaId);
        if (existente.isEmpty())
            return null;

        Resenia resenia = existente.get();
        resenia.setCalificacion(reseniaRequest.getCalificacion());
        resenia.setComentario(reseniaRequest.getComentario());
        resenia.setFechaCreacion(reseniaRequest.getFechaCreacion());
        return reseniaRepository.save(resenia);
    }

    public boolean deleteResenia(int reseniaId) {
        if (reseniaRepository.findById(reseniaId).isEmpty())
            return false;

        reseniaRepository.deleteById(reseniaId);
        return true;
    }
}
