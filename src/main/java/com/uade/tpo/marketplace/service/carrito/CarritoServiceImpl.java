package com.uade.tpo.marketplace.service.carrito;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.CarritoRequest;
import com.uade.tpo.marketplace.repository.ICarritoRepository;
import com.uade.tpo.marketplace.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarritoServiceImpl implements ICarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<Carrito> getCarritos(Integer usuarioId) {
        if (usuarioId != null)
            return carritoRepository.findByUsuario_UsuarioId(usuarioId);

        return carritoRepository.findAll();
    }

    public Optional<Carrito> getCarritoById(int carritoId) {
        return carritoRepository.findById(carritoId);
    }

    public Carrito crearCarrito(CarritoRequest carritoRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(carritoRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setFechaCarrito(carritoRequest.getFechaCarrito());
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCarrito(int carritoId, CarritoRequest carritoRequest) {
        Optional<Carrito> existente = carritoRepository.findById(carritoId);
        if (existente.isEmpty())
            return null;

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(carritoRequest.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        Carrito carrito = existente.get();
        carrito.setUsuario(usuario);
        carrito.setFechaCarrito(carritoRequest.getFechaCarrito());
        return carritoRepository.save(carrito);
    }

    public boolean deleteCarrito(int carritoId) {
        if (carritoRepository.findById(carritoId).isEmpty())
            return false;

        carritoRepository.deleteById(carritoId);
        return true;
    }
}
