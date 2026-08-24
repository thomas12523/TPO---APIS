package com.uade.tpo.marketplace.service.resenia;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.entity.dto.request.ReseniaRequest;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;

public interface IReseniaService {

    public Page<Resenia> getResenias(Integer productoId, PageRequest pageRequest);

    public Optional<Resenia> getReseniaById(int reseniaId);

    public Resenia crearResenia(ReseniaRequest reseniaRequest) throws ReseniaDuplicateException;

    public Optional<Resenia> actualizarResenia(int reseniaId, ReseniaRequest reseniaRequest);

    public boolean deleteResenia(int reseniaId);
}
