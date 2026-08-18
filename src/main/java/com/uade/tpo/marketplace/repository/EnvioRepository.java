package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Envio;

public class EnvioRepository {
    public ArrayList<Envio> envios = new ArrayList<>();

    public ArrayList<Envio> getEnvios() {
        return this.envios;
    }

    public Envio getEnvioById(int envioId) {
        for (Envio envio : this.envios) {
            if (envio.getEnvioId() == envioId) {
                return envio;
            }
        }
        return null;
    }

    public Envio crearEnvio(Envio envio) {
        this.envios.add(envio);
        return envio;
    }

    public Envio actualizarEnvio(int envioId, Envio envioActualizado) {
        Envio envio = getEnvioById(envioId);
        if (envio == null) {
            return null;
        }
        this.envios.remove(envio);
        this.envios.add(envioActualizado);
        return envioActualizado;
    }

    public boolean deleteEnvio(int envioId) {
        Envio envio = getEnvioById(envioId);
        if (envio == null) {
            return false;
        }
        return this.envios.remove(envio);
    }
}
