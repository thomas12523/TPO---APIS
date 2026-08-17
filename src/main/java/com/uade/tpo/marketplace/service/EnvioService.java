package com.uade.tpo.marketplace.service;

import java.util.ArrayList;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.repository.EnvioRepository;

public class EnvioService {

    public ArrayList<Envio> getEnvios() {
        EnvioRepository envioRepository = new EnvioRepository();
        return envioRepository.getEnvios();
    }

    public Envio getEnvioById(int envioId) {
        EnvioRepository envioRepository = new EnvioRepository();
        return envioRepository.getEnvioById(envioId);
    }

    public Envio crearEnvio(Envio envio) {
        EnvioRepository envioRepository = new EnvioRepository();
        return envioRepository.crearEnvio(envio);
    }

    public Envio actualizarEnvio(int envioId, Envio envio) {
        EnvioRepository envioRepository = new EnvioRepository();
        return envioRepository.actualizarEnvio(envioId, envio);
    }

    public boolean deleteEnvio(int envioId) {
        EnvioRepository envioRepository = new EnvioRepository();
        return envioRepository.deleteEnvio(envioId);
    }
}
