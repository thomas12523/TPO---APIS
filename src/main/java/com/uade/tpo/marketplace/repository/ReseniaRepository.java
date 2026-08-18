package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Resenia;

public class ReseniaRepository {
    private ArrayList<Resenia> resenias;

    public ReseniaRepository() {
        resenias = new ArrayList<Resenia>();
    }

    public ArrayList<Resenia> getResenias() {
        return this.resenias;
    }

    public Optional<Resenia> getReseniaById(int reseniaId) {
        return this.resenias.stream().filter(r -> r.getReseniaId() == reseniaId).findAny();
    }

    public Resenia crearResenia(Resenia resenia) {
        this.resenias.add(resenia);
        return resenia;
    }

    public Resenia actualizarResenia(int reseniaId, Resenia reseniaActualizada) {
        Optional<Resenia> resenia = getReseniaById(reseniaId);
        if (resenia.isEmpty()) {
            return null;
        }
        this.resenias.remove(resenia.get());
        this.resenias.add(reseniaActualizada);
        return reseniaActualizada;
    }

    public boolean deleteResenia(int reseniaId) {
        Optional<Resenia> resenia = getReseniaById(reseniaId);
        if (resenia.isEmpty()) {
            return false;
        }
        return this.resenias.remove(resenia.get());
    }
}
