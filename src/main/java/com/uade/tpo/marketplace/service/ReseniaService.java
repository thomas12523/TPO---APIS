package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Resenia;
import com.uade.tpo.marketplace.repository.ReseniaRepository;

public class ReseniaService {

    public ArrayList<Resenia> getResenias() {
        ReseniaRepository reseniaRepository = new ReseniaRepository();
        return reseniaRepository.getResenias();
    }

    public Resenia getReseniaById(int reseniaId) {
        ReseniaRepository reseniaRepository = new ReseniaRepository();
        return reseniaRepository.getReseniaById(reseniaId);
    }

    public Resenia crearResenia(Resenia resenia) {
        ReseniaRepository reseniaRepository = new ReseniaRepository();
        return reseniaRepository.crearResenia(resenia);
    }

    public Resenia actualizarResenia(int reseniaId, Resenia resenia) {
        ReseniaRepository reseniaRepository = new ReseniaRepository();
        return reseniaRepository.actualizarResenia(reseniaId, resenia);
    }

    public boolean deleteResenia(int reseniaId) {
        ReseniaRepository reseniaRepository = new ReseniaRepository();
        return reseniaRepository.deleteResenia(reseniaId);
    }
}
