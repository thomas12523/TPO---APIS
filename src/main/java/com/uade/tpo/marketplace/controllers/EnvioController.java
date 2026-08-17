package com.uade.tpo.marketplace.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.service.EnvioService;

@RestController
@RequestMapping("Envio")
public class EnvioController {

    @GetMapping
    public ArrayList<Envio> getEnvios() {
        EnvioService envioService = new EnvioService();
        return envioService.getEnvios();
    }

    @GetMapping("{envioId}")
    public Envio getEnvioById(@PathVariable int envioId) {
        EnvioService envioService = new EnvioService();
        return envioService.getEnvioById(envioId);
    }

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio entity) {
        EnvioService envioService = new EnvioService();
        return envioService.crearEnvio(entity);
    }

    @PutMapping("{envioId}")
    public Envio actualizarEnvio(@PathVariable int envioId, @RequestBody Envio entity) {
        EnvioService envioService = new EnvioService();
        return envioService.actualizarEnvio(envioId, entity);
    }

    @DeleteMapping("{envioId}")
    public boolean deleteEnvio(@PathVariable int envioId) {
        EnvioService envioService = new EnvioService();
        return envioService.deleteEnvio(envioId);
    }
}
