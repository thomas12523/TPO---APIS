package com.uade.tpo.marketplace.service.carrito;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Carrito;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.CarritoRequest;
import com.uade.tpo.marketplace.repository.ICarritoRepository;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarritoServiceImpl implements ICarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    public List<Carrito> getCarritos(Integer usuarioId) {
        if (usuarioId != null)
            return carritoRepository.findByUsuarioId(usuarioId);

        return carritoRepository.findAll();
    }

    public Optional<Carrito> getCarritoById(int carritoId) {
        return carritoRepository.findById(carritoId);
    }

    public Carrito crearCarrito(CarritoRequest carritoRequest) {
        Usuario usuario = usuarioService.getUsuarioById(carritoRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setFechaCarrito(carritoRequest.getFechaCarrito());
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCarrito(int carritoId, CarritoRequest carritoRequest) {
        Optional<Carrito> existente = carritoRepository.findById(carritoId);
        if (existente.isEmpty())
            return null;

        Usuario usuario = usuarioService.getUsuarioById(carritoRequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

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
