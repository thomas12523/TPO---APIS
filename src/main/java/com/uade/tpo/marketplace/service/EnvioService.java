package com.uade.tpo.marketplace.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.exceptions.EnvioDuplicateException;
import com.uade.tpo.marketplace.repository.EnvioRepository;

public class EnvioService {
    private EnvioRepository envioRepository;

    public EnvioService() {
        envioRepository = new EnvioRepository();
    }

    public ArrayList<Envio> getEnvios() {
        return envioRepository.getEnvios();
    }

    public Optional<Envio> getEnvioById(int envioId) {
        return envioRepository.getEnvioById(envioId);
    }

    public Envio crearEnvio(Envio envio) throws EnvioDuplicateException {
        if (envioRepository.getEnvioById(envio.getEnvioId()).isPresent())
            throw new EnvioDuplicateException();
        return envioRepository.crearEnvio(envio);
    }

    public Envio actualizarEnvio(int envioId, Envio envio) {
        return envioRepository.actualizarEnvio(envioId, envio);
    }

    public boolean deleteEnvio(int envioId) {
        return envioRepository.deleteEnvio(envioId);
    }
}
