package com.uade.tpo.marketplace.service.resenia;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.dto.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;

public interface ReseniaService {

    public List<Resenia> getResenias(Integer productoId);

    public Optional<Resenia> getReseniaById(int reseniaId);

    public Resenia crearResenia(ReseniaRequest reseniaRequest) throws ReseniaDuplicateException;

    public Resenia actualizarResenia(int reseniaId, ReseniaRequest reseniaRequest);

    public boolean deleteResenia(int reseniaId);
}
