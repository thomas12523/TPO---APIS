package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.exceptions.ReseniaDuplicateException;
import com.uade.tpo.marketplace.repository.ReseniaRepository;

public class ReseniaService {
    private ReseniaRepository reseniaRepository;

    public ReseniaService() {
        reseniaRepository = new ReseniaRepository();
    }

    public ArrayList<Resenia> getResenias() {
        return reseniaRepository.getResenias();
    }

    public Optional<Resenia> getReseniaById(int reseniaId) {
        return reseniaRepository.getReseniaById(reseniaId);
    }

    public Resenia crearResenia(Resenia resenia) throws ReseniaDuplicateException {
        if (reseniaRepository.getReseniaById(resenia.getReseniaId()).isPresent())
            throw new ReseniaDuplicateException();
        return reseniaRepository.crearResenia(resenia);
    }

    public Resenia actualizarResenia(int reseniaId, Resenia resenia) {
        return reseniaRepository.actualizarResenia(reseniaId, resenia);
    }

    public boolean deleteResenia(int reseniaId) {
        return reseniaRepository.deleteResenia(reseniaId);
    }
}
