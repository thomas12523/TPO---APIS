package com.uade.tpo.marketplace.service.resena;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Resena;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.ResenaRequest;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.exceptions.ResenaDuplicateException;
import com.uade.tpo.marketplace.exceptions.ResenaInvalidaException;

public interface IResenaService {

    public List<Resena> getResenas(Integer productoId);

    public Optional<Resena> getResenaById(int resenaId);

    public Resena crearResena(ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException, ResenaDuplicateException, ProductoNotFoundException;

    public Optional<Resena> actualizarResena(int resenaId, ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException;

    public Optional<Resena> deleteResena(int resenaId, Usuario usuarioActual);
}
