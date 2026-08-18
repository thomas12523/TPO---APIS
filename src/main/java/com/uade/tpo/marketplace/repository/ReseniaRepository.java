package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Resenia;

public class ReseniaRepository {
    public ArrayList<Resenia> resenias = new ArrayList<>();

    public ArrayList<Resenia> getResenias() {
        return this.resenias;
    }

    public Resenia getReseniaById(int reseniaId) {
        for (Resenia resenia : this.resenias) {
            if (resenia.getReseniaId() == reseniaId) {
                return resenia;
            }
        }
        return null;
    }

    public Resenia crearResenia(Resenia resenia) {
        this.resenias.add(resenia);
        return resenia;
    }

    public Resenia actualizarResenia(int reseniaId, Resenia reseniaActualizada) {
        Resenia resenia = getReseniaById(reseniaId);
        if (resenia == null) {
            return null;
        }
        this.resenias.remove(resenia);
        this.resenias.add(reseniaActualizada);
        return reseniaActualizada;
    }

    public boolean deleteResenia(int reseniaId) {
        Resenia resenia = getReseniaById(reseniaId);
        if (resenia == null) {
            return false;
        }
        return this.resenias.remove(resenia);
    }
}
