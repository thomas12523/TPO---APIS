package com.uade.tpo.marketplace.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Envio;
import com.uade.tpo.marketplace.entity.dto.EnvioRequest;
import com.uade.tpo.marketplace.exceptions.EnvioDuplicateException;
import com.uade.tpo.marketplace.service.EnvioService;

@RestController
@RequestMapping("Envio")
public class EnvioController {
    private EnvioService envioService;

    public EnvioController() {
        envioService = new EnvioService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Envio>> getEnvios() {
        return ResponseEntity.ok(envioService.getEnvios());
    }

    @GetMapping("{envioId}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable int envioId) {
        Optional<Envio> result = envioService.getEnvioById(envioId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearEnvio(@RequestBody EnvioRequest envioRequest) throws EnvioDuplicateException {
        Envio envio = Envio.builder()
                .envioId(envioRequest.getEnvioId())
                .pedidoId(envioRequest.getPedidoId())
                .direccion(envioRequest.getDireccion())
                .metodoEnvio(envioRequest.getMetodoEnvio())
                .costoEnvio(envioRequest.getCostoEnvio())
                .build();
        Envio result = envioService.crearEnvio(envio);
        return ResponseEntity.created(URI.create("/Envio/" + result.getEnvioId())).body(result);
    }

    @PutMapping("{envioId}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable int envioId, @RequestBody Envio entity) {
        Envio result = envioService.actualizarEnvio(envioId, entity);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{envioId}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable int envioId) {
        boolean deleted = envioService.deleteEnvio(envioId);
        if (!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
