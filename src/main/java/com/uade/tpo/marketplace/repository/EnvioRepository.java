package com.uade.tpo.marketplace.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Envio;

public class EnvioRepository {
    private ArrayList<Envio> envios;

    public EnvioRepository() {
        envios = new ArrayList<Envio>();
    }

    public ArrayList<Envio> getEnvios() {
        return this.envios;
    }

    public Optional<Envio> getEnvioById(int envioId) {
        return this.envios.stream().filter(e -> e.getEnvioId() == envioId).findAny();
    }

    public Envio crearEnvio(Envio envio) {
        this.envios.add(envio);
        return envio;
    }

    public Envio actualizarEnvio(int envioId, Envio envioActualizado) {
        Optional<Envio> envio = getEnvioById(envioId);
        if (envio.isEmpty()) {
            return null;
        }
        this.envios.remove(envio.get());
        this.envios.add(envioActualizado);
        return envioActualizado;
    }

    public boolean deleteEnvio(int envioId) {
        Optional<Envio> envio = getEnvioById(envioId);
        if (envio.isEmpty()) {
            return false;
        }
        return this.envios.remove(envio.get());
    }
}
