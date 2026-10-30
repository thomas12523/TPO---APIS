package com.uade.tpo.marketplace.service.resena;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Resena;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.ResenaRequest;
import com.uade.tpo.marketplace.exceptions.ProductoNotFoundException;
import com.uade.tpo.marketplace.exceptions.ResenaDuplicateException;
import com.uade.tpo.marketplace.exceptions.ResenaInvalidaException;
import com.uade.tpo.marketplace.exceptions.ResenaSinCompraException;

public interface IResenaService {

    public List<Resena> getResenas(Integer productoId);

    public Optional<Resena> getResenaById(int resenaId);

    public boolean compro(int productoId, Usuario usuarioActual);

    public Resena crearResena(ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException, ResenaDuplicateException, ProductoNotFoundException, ResenaSinCompraException;

    public Optional<Resena> actualizarResena(int resenaId, ResenaRequest resenaRequest, Usuario usuarioActual)
            throws ResenaInvalidaException;

    public Optional<Resena> deleteResena(int resenaId, Usuario usuarioActual);
}
